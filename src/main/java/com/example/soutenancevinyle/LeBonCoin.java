package com.example.soutenancevinyle;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;


import java.util.ArrayList;
import java.util.List;

public class LeBonCoin{
    public ArrayList<Scraping> listVinyles = new ArrayList<>();

    public String searchBC(String search, int min, int max) throws Exception {

        String url = "https://www.leboncoin.fr/recherche?category=26&text=" + search + "&price=" + min + "-" + max;

        WebClient webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        List<HtmlAnchor> links = htmlPage.getByXPath("//a[@data-qa-id='aditem_container']"); // liste des liens
        int limit = 5;
        String res = null;

        for (int i = 0; i < links.size(); i++) {
            if (i == limit)
                break;
            String resultUrl = String.valueOf(links.get(i).click().getUrl());
            try {

                HtmlPage htmlPage1 = webClient.getPage(resultUrl);
                String title = ((HtmlHeading1) htmlPage1.getByXPath(".//h1[@data-qa-id='adview_title']").get(0)).getTextContent();
                String price = ((HtmlDivision) htmlPage1.getByXPath(".//div[@data-qa-id='adview_price']").get(0)).getTextContent();
                String description = ((HtmlDivision) htmlPage1.getByXPath(".//div[@data-qa-id='adview_description_container']").get(0)).getTextContent();
                String date = ((HtmlElement) htmlPage1.getByXPath("/html/body/main/section/div[2]/div/div/div/section/div[1]/div[2]/div[1]/p[2]/strong").get(0)).getTextContent();
                Scraping s = new Scraping(title, price, description, resultUrl);

                listVinyles.add(s);

                res += s.toString();
                System.out.println(resultUrl);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}



