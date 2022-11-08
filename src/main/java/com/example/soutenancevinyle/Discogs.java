package com.example.soutenancevinyle;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public class Discogs {
    public String searchVC(String search) throws Exception{

        String url = "https://www.vinylcorner.fr/recherche?controller=search&s=" + search;

        WebClient webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        List<HtmlAnchor> links = htmlPage.getByXPath(".//a[@class='item_description_title']"); // liste des liens
        // search_result_title
        String show = "";
        int limit = 5; // limitation a 5 recherches
        for (int i = 0; i < links.size() ; i++) {
            if(i == limit)
                break;

            String resultUrl = String.valueOf(links.get(i).click().getUrl()); //

            try{
                HtmlPage htmlPage1 = webClient.getPage(resultUrl);
                String title = ((HtmlHeading1) htmlPage1.getByXPath(".//h1[@id='profile_title']").get(0)).getTextContent();

                // recuperation du prix
                //String price = ((HtmlDivision) htmlPage1.getByXPath(".//html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/h1").get(0)).getTextContent();
               //String str = price.replaceAll("\\D", " "); // Remplacer chaque nombre non numerique par un espace
                //str = str.trim(); // Supprimer les espaces de debut et de fin
                //price = str.replaceAll(" +", ""); // Remplacez les espaces consécutifs par un seul espace
               // int priceInt = Integer.parseInt(price); // Convertir le String en int

                //String description = ((HtmlDivision) htmlPage1.getByXPath(".//div[@data-qa-id='adview_description_container']").get(0)).getTextContent();

                show += "Url : " + resultUrl + "\n" +
                        "Titre : " + title;
                        //"Prix : " + priceInt + " €" + "\n" +
                        //"Description : " + description +
                        //"\n----------------------------\n";
                System.out.println(show);
                //TODO if(onClickSave())
                //write(show);

            } catch(Exception e){
                e.printStackTrace();
            }

        }

        return show;
    }
}
