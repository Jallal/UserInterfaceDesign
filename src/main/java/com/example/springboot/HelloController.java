package com.example.springboot;

import com.opencsv.CSVReader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {
    public List<com.example.springboot.PublisherInfo> data = new ArrayList<>();

    @RequestMapping("/index")
    public String loginMessage() throws IOException {
        this.data = this.readTheDataFromVCS("coursesList.csv");
        return "index";
    }




    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody com.example.springboot.SearchCriteria search, Errors errors) throws Exception {
        System.out.print("******************************************************\n");
        System.out.print(search.toString());
        System.out.print("******************************************************\n");
        com.example.springboot.AjaxResponseBody result = new com.example.springboot.AjaxResponseBody();
        result.setMsg("success");
        result.setResult(data);
        return ResponseEntity.ok(data);
    }

    public List<com.example.springboot.PublisherInfo> readTheDataFromVCS(String csvFile) throws IOException {

        File file = ResourceUtils.getFile("classpath:" + csvFile);
        CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(file)));
        List<com.example.springboot.PublisherInfo> publishRecords = new ArrayList<>();
        String[] values = (csvReader.readNext());
        while (values != null) {
            List<String> recordsString = Arrays.asList(values);
            com.example.springboot.PublisherInfo publisherInfo = new com.example.springboot.PublisherInfo(
                    recordsString.get(0),
                    recordsString.get(1),
                    recordsString.get(2),
                    recordsString.get(3),
                    recordsString.get(4),
                    recordsString.get(5),
                    recordsString.get(6),
                    recordsString.get(7),
                    recordsString.get(8),
                    recordsString.get(9));
            publishRecords.add(publisherInfo);
            values = csvReader.readNext();
        }

        publishRecords.remove(0);
        return publishRecords;
    }
}
