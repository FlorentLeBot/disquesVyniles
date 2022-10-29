package com.example.soutenancevinyle;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class ScrolingBC {
    public String search(String word) throws Exception{

        String url = "https://www.leboncoin.fr/recherche?text=vinyle" + " " + word;

        WebClient webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        List<HtmlAnchor> links = htmlPage.getByXPath("//a[@data-qa-id='aditem_container']"); // liste des liens

        String show = "";
        int limit = 5;
        for (int i = 0; i < links.size() ; i++) {
            if(i == limit)
                break;

            String resultUrl = String.valueOf(links.get(i).click().getUrl());

            try{
                HtmlPage htmlPage1 = webClient.getPage(resultUrl);

                String title = ((HtmlHeading1) htmlPage1.getByXPath(".//h1[@data-qa-id='adview_title']").get(0)).getTextContent();

                // recuperation du prix
                String price = ((HtmlDivision) htmlPage1.getByXPath(".//div[@data-qa-id='adview_price']").get(0)).getTextContent();
                String str = price.replaceAll("\\D", " "); // Remplacer chaque nombre non numerique par un espace
                str = str.trim(); // Supprimer les espaces de debut et de fin
                price = str.replaceAll(" +", ""); // Remplacez les espaces consécutifs par un seul espace
                int priceInt = Integer.parseInt(price); // Convertir le String en int

                String description = ((HtmlDivision) htmlPage1.getByXPath(".//div[@data-qa-id='adview_description_container']").get(0)).getTextContent();

                show += "Url : " + resultUrl + "\n" +
                        "Titre : " + title + "\n" +
                        "Prix : " + priceInt + " €" + "\n" +
                        "Description : " + description +
                        "\n----------------------------\n";
                System.out.println(show);
                //TODO if(onClickSave())
                    write(show);

            } catch(Exception e){
                e.printStackTrace();
            }

        }

        return show;
    }

    public void write(String res) throws IOException {
        //Ecrire dans un fichier texte
        String file = "vinyle.txt";
        PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        write.println(res);
        write.close();
    }


}
