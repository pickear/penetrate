package com.weasel.penetrate.monitor;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by dell on 2017/3/23.
 */
public class FrpMonitor {

    public static void main(String[] args) throws IOException {

        String authorization = Base64.getEncoder().encodeToString("admin:kisme".getBytes(Charset.forName("utf-8")));

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.addRequestHeader("Authorization","Basic "+authorization);
        HtmlPage htmlPage = webClient.getPage("http://127.0.0.1:7998/frp-status");
        Iterable<DomElement> domElements = htmlPage.getElementById("tab_body").getChildElements();
        Set<DeviceStatus> deviceStatuss = new HashSet<>();
        domElements.forEach(domElement -> {
            DeviceStatus deviceStatus = new DeviceStatus();
            Iterable<DomElement> domElementss = domElement.getChildElements();
            int index = 0;
            for(Iterator it = domElementss.iterator();it.hasNext();index++){
                DomElement aaa = (DomElement)it.next();
                String content = aaa.getTextContent();
                content = StringUtils.removeAll(content,"\n*\t*");
                switch (index){
                    case 0:
                        deviceStatus.setNumber(content);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        deviceStatus.setStatus(content);
                        break;
                    case 4:
                        deviceStatus.setConnects(Long.valueOf(content));
                        break;
                    case 5:
                        deviceStatus.setFlowOut(Long.valueOf(content));
                        break;
                    case 6:
                        deviceStatus.setFlowIn(Long.valueOf(content));
                        break;
                    case 7:
                        deviceStatus.setTotalConnects(Long.valueOf(content));
                        break;
                    default:break;
                }
            }
            deviceStatuss.add(deviceStatus);
        });
        webClient.close();
    }
}
