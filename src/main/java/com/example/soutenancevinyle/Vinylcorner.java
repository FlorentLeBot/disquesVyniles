package com.example.soutenancevinyle;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.List;

public class Vinylcorner {

    public ArrayList<Scraping> listVinyles = new ArrayList<>();
    public String searchVC(String search) throws Exception{

        String url = "https://www.vinylcorner.fr/recherche?controller=search&s=" +  search.replaceAll(" ", "+");

        WebClient webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        List<HtmlElement> links = htmlPage.getByXPath("//h2/a"); // liste des liens

        int limit = 5;
        String res = null;

        for (int i = 0; i < links.size(); i++) {
            if (i == limit)
                break;
            String resultUrl = String.valueOf(links.get(i).click().getUrl());
            try {

                HtmlPage htmlPage1 = webClient.getPage(resultUrl);
                String title = ((HtmlHeading1) htmlPage1.getByXPath(".//h1[@class='productpage_title']").get(0)).getTextContent();
                String price = ((HtmlElement) htmlPage1.getByXPath("/html/body/main/section/div[2]/div/div/div/section/div[1]/div[2]/div[2]/div[1]/div/span").get(0)).getTextContent();
                String type = ((HtmlElement) htmlPage1.getByXPath("/html/body/main/section/div[2]/div/div/div/section/div[1]/div[2]/div[1]/p[4]").get(0)).getTextContent();
                String description = ((HtmlDivision) htmlPage1.getByXPath(".//div[@class='features']").get(0)).getTextContent();
                description = description.replaceAll("\\s+", "  ").replaceAll("\\n", " ").trim();
                String date = ((HtmlElement) htmlPage1.getByXPath("/html/body/main/section/div[2]/div/div/div/section/div[1]/div[2]/div[1]/p[2]/strong").get(0)).getTextContent();
                date = date.replaceAll(",", "");

                Scraping s = new Scraping(title, price, description, resultUrl);

                listVinyles.add(s);

                /*
                res +=  "Url : " + resultUrl + "\n" +
                        "Titre : " + title + "\n" +
                        "Prix : " + price + "\n" +
                        "Type : " + type + "\n" +
                        "Date : " + date + "\n" +
                        "Description : " + description;
                 */
                res += s.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}