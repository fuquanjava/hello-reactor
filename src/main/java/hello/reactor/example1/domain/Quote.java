package hello.reactor.example1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/11/24 17:28
 * description:
 */
//@JsonIgnoreProperties(ignoreUnknown=true) signals that any other attributes are to be ignored.
@JsonIgnoreProperties(ignoreUnknown=true)
public class Quote implements Serializable {
    private static final long serialVersionUID = -4902391024862470230L;

    private Long id;

    private String quote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
