package net.java.dev.wlanapi.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Descirption
 * @Author WuXiang
 * @Date 2018/9/28/
 */
@XmlRootElement(name = "MacRandomization")
@XmlAccessorType(XmlAccessType.FIELD)
public class MacRandomization {
    @XmlAttribute( name ="xmlns")
    private String xmlns = "http://www.microsoft.com/networking/WLAN/profile/v3";

    private String enableRandomization;

    private String randomizationSeed;


    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getEnableRandomization() {
        return enableRandomization;
    }

    public void setEnableRandomization(String enableRandomization) {
        this.enableRandomization = enableRandomization;
    }

    public String getRandomizationSeed() {
        return randomizationSeed;
    }

    public void setRandomizationSeed(String randomizationSeed) {
        this.randomizationSeed = randomizationSeed;
    }
}
