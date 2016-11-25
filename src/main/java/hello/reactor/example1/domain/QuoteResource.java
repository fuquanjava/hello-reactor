package hello.reactor.example1.domain;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/11/24 17:33
 * description:
 */
public class QuoteResource implements Serializable {
    private static final long serialVersionUID = 371893251052302712L;

    private String type;

    private Quote value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Quote getValue() {
        return value;
    }

    public void setValue(Quote value) {
        this.value = value;
    }
}
