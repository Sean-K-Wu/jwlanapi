package net.java.dev.wlanapi.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Descirption
 * @Author WuXiang
 * @Date 2018/9/27/
 */
@XmlRootElement(name = "MSM")
@XmlAccessorType(XmlAccessType.FIELD)
public class MSM {
    private Security security;

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
