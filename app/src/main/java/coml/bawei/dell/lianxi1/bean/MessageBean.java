package coml.bawei.dell.lianxi1.bean;

import java.util.List;

public class MessageBean {
   String falg;
   Object message;

    public MessageBean(String falg, Object message) {
        this.falg = falg;
        this.message = message;
    }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
