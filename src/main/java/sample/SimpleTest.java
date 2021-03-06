package sample;

import net.java.dev.wlanapi.ScanResult;
import net.java.dev.wlanapi.WifiManager;
import net.java.dev.wlanapi.xml.WLANProfileXml;
import net.java.dev.wlanapi.xml.adapter.NullPolicyListener;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by i.perezdealbeniz on 29/12/2016.
 */
public class SimpleTest {

    private  WifiManager wlan_manager = null;




    @Before
    public void init(){
        wlan_manager = new WifiManager();
        System.out.printf("Wifi Enabled: %s\n\n",wlan_manager.isWifiEnabled());
    }

    @Test
    public void testGetProfile(){
        String vision_5G = wlan_manager.getNetwork("Vision_5G");
        System.out.println(vision_5G);
    }
    @Test
    public void testSetProfile(){
        String sourceStr = "<?xml version=\"1.0\"?>\n" +
                "<WLANProfile xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v1\">\n" +
                "\t<name>Vision_5G</name>\n" +
                "\t<SSIDConfig>\n" +
                "\t\t<SSID>\n" +
                "\t\t\t<hex>566973696F6E5F3547</hex>\n" +
                "\t\t\t<name>Vision_5G</name>\n" +
                "\t\t</SSID>\n" +
                "\t</SSIDConfig>\n" +
                "\t<connectionType>ESS</connectionType>\n" +
                "\t<connectionMode>auto</connectionMode>\n" +
                "\t<MSM>\n" +
                "\t\t<security>\n" +
                "\t\t\t<authEncryption>\n" +
                "\t\t\t\t<authentication>WPA2PSK</authentication>\n" +
                "\t\t\t\t<encryption>AES</encryption>\n" +
                "\t\t\t\t<useOneX>false</useOneX>\n" +
                "\t\t\t</authEncryption>\n" +
                "\t\t\t<sharedKey>\n" +
                "\t\t\t\t<keyType>passPhrase</keyType>\n" +
                "\t\t\t\t<protected>true</protected>\n" +
                "\t\t\t\t<keyMaterial>01000000D08C9DDF0115D1118C7A00C04FC297EB01000000670BCDE24465664FB25E66E3FA50FE910000000002000000000010660000000100002000000025167B0307D5A31973A4ED25CAF9294D1EE922F721F2E02C0EA1875EA4AD1C77000000000E80000000020000200000005C2AEC0BA2C4D7E9A692E5E4A263BBA192B1C6F066046D405D86258B16D5EC7D10000000F0E8B1AFCEEAEA4D9C5707A2A7D9F61B4000000071FBE7215024E662CE15FCEDD9CC2B8A0A0D7E79B4C899D04578501568699054876B445804CB62FF7754B7E26E3B12585F968CF4D7B869406CB4F2141F0D76E1</keyMaterial>\n" +
                "\t\t\t</sharedKey>\n" +
                "\t\t</security>\n" +
                "\t</MSM>\n" +
                "\t<MacRandomization xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v3\">\n" +
                "\t\t<enableRandomization>false</enableRandomization>\n" +
                "\t\t<randomizationSeed>2158375862</randomizationSeed>\n" +
                "\t</MacRandomization>\n" +
                "</WLANProfile>";
        try (StringReader stringReader = new StringReader(sourceStr);StringWriter sw = new StringWriter();){
            JAXBContext jaxbContext = JAXBContext.newInstance(WLANProfileXml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(stringReader));
            WLANProfileXml wlanProfileXml = (WLANProfileXml) unmarshaller.unmarshal(source);
            //wlanProfileXml.setName("测试");
            wlanProfileXml.setConnectionMode("manual");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.setListener(new NullPolicyListener());
            marshaller.marshal(wlanProfileXml,sw);
            System.out.println(sw.toString());
            boolean b = wlan_manager.addNetwork(sw.toString());
            System.out.println(b);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetConnected(){
        System.out.println("Connected Wifi Networks:");
        System.out.println("\t" + wlan_manager.connectedSsid());
    }
    @Test
    public void testScan(){
        try {
            System.out.println("Detected Wifi Networks:\n");
            List<ScanResult> scan_results = wlan_manager.waitForScanResults(5,
                    TimeUnit.SECONDS);
            for(int i=0; i < scan_results.size(); i++){
                System.out.println("\t SSID : "+scan_results.get(i).SSID);
                System.out.println("\t\t BSSID : "+scan_results.get(i).BSSID);
                System.out.println("\t\t Frequency (MHz): "+
                        Integer.toString(scan_results.get(i).frequency));
                System.out.println("\t\t Signal Level (db) : "+
                        Integer.toString(scan_results.get(i).level));
                System.out.println("\t\t Capabilities : "+
                        scan_results.get(i).capabilities);
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetConfiguredList(){
        System.out.println("Configured Wifi Networks:\n");
        List<String> configured_networks = wlan_manager.getConfiguredNetworks();
        for (int i = 0; i < configured_networks.size(); i++) {
            System.out.println((i+1)+".   \t"+configured_networks.get(i));
        }
    }
    @Test
    public void testConnect(){
        try {
            System.out.println("Connecting Wifi Networks:");
            System.out.println("\t" + wlan_manager.waitForConnect("Vision_5G",5,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testDisconnect(){
        try {
            System.out.println("Release Connected Wifi Networks:");
            wlan_manager.waitForDisconnect(3,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOutXml(){

        try(StringWriter sw = new StringWriter()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(WLANProfileXml.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.setListener(new NullPolicyListener());
            marshaller.marshal(new WLANProfileXml(),sw);
            System.out.println(sw.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testConvertXml(){
        String sourceStr = "<?xml version=\"1.0\"?>\n" +
                "<WLANProfile xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v1\">\n" +
                "\t<name>Vision_5G</name>\n" +
                "\t<SSIDConfig>\n" +
                "\t\t<SSID>\n" +
                "\t\t\t<hex>566973696F6E5F3547</hex>\n" +
                "\t\t\t<name>Vision_5G</name>\n" +
                "\t\t</SSID>\n" +
                "\t</SSIDConfig>\n" +
                "\t<connectionType>ESS</connectionType>\n" +
                "\t<connectionMode>auto</connectionMode>\n" +
                "\t<MSM>\n" +
                "\t\t<security>\n" +
                "\t\t\t<authEncryption>\n" +
                "\t\t\t\t<authentication>WPA2PSK</authentication>\n" +
                "\t\t\t\t<encryption>AES</encryption>\n" +
                "\t\t\t\t<useOneX>false</useOneX>\n" +
                "\t\t\t</authEncryption>\n" +
                "\t\t\t<sharedKey>\n" +
                "\t\t\t\t<keyType>passPhrase</keyType>\n" +
                "\t\t\t\t<protected>true</protected>\n" +
                "\t\t\t\t<keyMaterial>01000000D08C9DDF0115D1118C7A00C04FC297EB01000000670BCDE24465664FB25E66E3FA50FE910000000002000000000010660000000100002000000025167B0307D5A31973A4ED25CAF9294D1EE922F721F2E02C0EA1875EA4AD1C77000000000E80000000020000200000005C2AEC0BA2C4D7E9A692E5E4A263BBA192B1C6F066046D405D86258B16D5EC7D10000000F0E8B1AFCEEAEA4D9C5707A2A7D9F61B4000000071FBE7215024E662CE15FCEDD9CC2B8A0A0D7E79B4C899D04578501568699054876B445804CB62FF7754B7E26E3B12585F968CF4D7B869406CB4F2141F0D76E1</keyMaterial>\n" +
                "\t\t\t</sharedKey>\n" +
                "\t\t</security>\n" +
                "\t</MSM>\n" +
                "\t<MacRandomization xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v3\">\n" +
                "\t\t<enableRandomization>false</enableRandomization>\n" +
                "\t\t<randomizationSeed>2158375862</randomizationSeed>\n" +
                "\t</MacRandomization>\n" +
                "</WLANProfile>";
        try (StringReader stringReader = new StringReader(sourceStr); ){
            JAXBContext jaxbContext = JAXBContext.newInstance(WLANProfileXml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(stringReader));
            WLANProfileXml wlanProfileXml = (WLANProfileXml) unmarshaller.unmarshal(source);
            System.out.println(wlanProfileXml);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }


}
