package com.weasel.penetrate.monitor;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

/**
 * Created by dell on 2017/3/23.
 */
public class FrpMonitor {

    public static void main(String[] args) throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.addRequestHeader("Authorization","Basic YWRtaW46a2lzbWU=");
        HtmlPage htmlPage = webClient.getPage("http://127.0.0.1:5003");
        String text = htmlPage.asText();
        webClient.close();
    }
}
