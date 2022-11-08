package com.example.soutenancevinyle;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

public class Mesvinyles {

    public ArrayList<Scraping> listVinyles = new ArrayList<>();

    public String searchMV(String search) throws Exception{

        String url = "https://mesvinyles.fr/fr/recherche?controller=search&s="+ search;

        WebClient webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        List<HtmlAnchor> links = htmlPage.getByXPath("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div/ul/li[*]/div/div[2]/h3/a");

        int limit = 5;
        String res = null;

        for (int i = 0; i < links.size() ; i++) {
            if(i == limit) {
                break;
            }
            String resultUrl = String.valueOf(links.get(i).click().getUrl());

            try{
                HtmlPage htmlPage1 = webClient.getPage(resultUrl);
                // recuperation du titre
                String title = ((HtmlHeading1) htmlPage1.getByXPath(".//h1[@class='h1 productpage_title']").get(0)).getTextContent();
                // recuperation du prix
                String price = ((HtmlSpan) htmlPage1.getByXPath(".//span[@itemprop='price']").get(0)).getTextContent();
                // recuperation de la date
                //String date = ((HtmlParagraph) htmlPage1.getByXPath("/html/body/main/section/div/div/div/section/div[1]/div[2]/div[2]/div[1]/p[1]"));
                // recuperation de la description
                String description = ((HtmlDivision) htmlPage1.getByXPath(".//div[@class='product-description']").get(0)).getTextContent();

                Scraping s = new Scraping(title, price, description, resultUrl);

                listVinyles.add(s);

                res += s.toString();

            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }

}
