package com.weasel.penetrate.monitor;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

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
        List<DeviceStatus> deviceStatuss = new ArrayList<>();
        domElements.forEach(domElement -> {
            DeviceStatus deviceStatus = new DeviceStatus();
            Iterable<DomElement> domElementss = domElement.getChildElements();
            int index = 0;
            for(Iterator it = domElementss.iterator();it.hasNext();index++){
                DomElement aaa = (DomElement)it.next();
                String content = aaa.getTextContent();
                switch (index){
                    case 0:
                        deviceStatus.setNumber(content);
                    case 1:
                        deviceStatus.setStatus(content);
                    case 2:
                        deviceStatus.setConnects(Long.valueOf(content));
                    case 3:
                        deviceStatus.setFlowOut(Long.valueOf(content));
                    case 4:
                        deviceStatus.setFlowIn(Long.valueOf(content));
                    case 5:
                        deviceStatus.setTotalConnects(Long.valueOf(content));
                    default:return;
                }
            }
            deviceStatuss.add(deviceStatus);
        });
        webClient.close();
    }
}
