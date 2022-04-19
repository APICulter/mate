package com.afd.mate.alphavantage;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AlphaVantageTimeSeriesDailyJsonTest {
    @SneakyThrows
    @Test
    void matchesJson(){
        ObjectMapper mapper = new ObjectMapper();

        File file = new ClassPathResource("alphavantage-samples/timeseries-daily.json").getFile();
        AlphaVantageTimeSeriesDailyJson json = mapper.readValue(file, AlphaVantageTimeSeriesDailyJson.class);

        assertThat(json).isNotNull();
    }
}