package com.address.book.dao;

import com.address.book.model.Address;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.address.book.model.Gender;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AddressDAO implements IAddressDAO{

    private static final Logger logger = LogManager.getLogger(AddressDAO.class.getName());
    private static final PropertiesUtil configProperties = new PropertiesUtil("application.properties");
    @Override
    public List<Address> read() {

        List<Address> addressList = new ArrayList<>();
        List<String> dataLines = readFile(configProperties.getStringProperty("file_name"));

        for (String dataLine : dataLines) {
            StringTokenizer st = new StringTokenizer(dataLine, " ,");

            String firstName = st.nextToken();
            String lastName = st.nextToken();
            Gender gender = Gender.valueOf(st.nextToken());


            String dateFormat = configProperties.getStringProperty("date_format");

            DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
            DateTime birthday = formatter.parseDateTime(st.nextToken());

            Address address = new Address(firstName, lastName, gender, birthday);
            addressList.add(address);
        }
        return addressList;
    }

    private List<String> readFile(String entityName){
        List<String> readLines = new ArrayList<>();
        String dirPath = configProperties.getStringProperty("data_path");
        String fileName = configProperties.getStringProperty("file_name");
        Path path = FileSystems.getDefault().getPath(dirPath, fileName);
        try {
            readLines = Files.readAllLines(path, Charset.defaultCharset());
            return readLines;
        } catch (IOException e) {
            logger.error("Error accessing data file: " + entityName, e);
        }
      return readLines;
    }
}
