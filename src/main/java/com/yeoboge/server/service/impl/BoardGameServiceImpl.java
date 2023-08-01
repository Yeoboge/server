package com.yeoboge.server.service.impl;

import com.yeoboge.server.service.BoardGameService;
import com.yeoboge.server.utils.CsvParsing;
import com.yeoboge.server.utils.GetKorName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardGameServiceImpl implements BoardGameService {
    @Override
    public void saveBoardGame() throws IOException {
        CsvParsing boardGameCsv = new CsvParsing("C:\\Users\\HeongJi\\Desktop\\여보게\\games.txt");
        String[] line = null;
        int lineCount = 0;
        int boardGameCount = 0;
        while ((line = boardGameCsv.nextRead()) != null) {
            if (lineCount == 0) {
                System.out.println(line[40]);
                lineCount++;
                continue;
            }
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://api.geekdo.com/xmlapi/boardgame/"+ line[0];
            boolean isValid = false;
            for (int i = 0; i < 8; i++) {
                if (line[40+i].equals("1")){
                    isValid=true;
                    break;
                }
            }
            if (!isValid) continue;
            String xmlData = restTemplate.getForObject(apiUrl, String.class);
            String korName = GetKorName.getKorName(xmlData);
            if (!korName.equals("")) {
                boardGameCount++;
                System.out.println("한글화됨");
                System.out.println(korName);
            }
        }
        System.out.println("전체 갯수 : " + boardGameCount);
    }
}
