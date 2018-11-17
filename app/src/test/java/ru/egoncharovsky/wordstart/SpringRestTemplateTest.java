package ru.egoncharovsky.wordstart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class SpringRestTemplateTest {

    @Test
    public void simpleGetRequest() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        Assert.assertEquals(quote.getType(), "success");
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Quote {

        private String type;
        private Value value;

        public Quote() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Quote{" +
                    "type='" + type + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Value {

        private Long id;
        private String quote;

        public Value() {
        }

        public Long getId() {
            return this.id;
        }

        public String getQuote() {
            return this.quote;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "id=" + id +
                    ", quote='" + quote + '\'' +
                    '}';
        }
    }
}
