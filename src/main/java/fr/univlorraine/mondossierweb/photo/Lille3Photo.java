//package org.esupportail.mondossierweb.web.photo;
package fr.univlorraine.mondossierweb.photo;

//import org.esupportail.commons.services.logging.Logger;
//import org.esupportail.commons.services.logging.LoggerImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;

import com.vaadin.server.WebBrowser;

import fr.univlorraine.mondossierweb.GenericUI;
import fr.univlorraine.mondossierweb.MainUI;
import fr.univlorraine.mondossierweb.MdwTouchkitUI;
//import fr.univnancy2.PhotoClient.beans.Category;
//import fr.univnancy2.PhotoClient.beans.PhotoClient;
//import fr.univnancy2.PhotoClient.beans.TicketClient;
//import fr.univnancy2.PhotoClient.exception.PhotoClientException;

/**
 * classe pour la gestion des photos (notament la récupération du ticket).
 * @author Mathieu Hetru
 */

@Scope(value="session", proxyMode=ScopedProxyMode.DEFAULT)
@Component(value="Lille3Photo")
public class Lille3Photo implements IPhoto {
    /**
      * Un logger.
      */
    private static final Logger LOG = LoggerFactory.getLogger(Lille3Photo.class);
    /**
     * Une URL
     */
    //private String photoUrl = "https://refphotos.univ.fr/";
    
    private String imageurl = null;
    private String tokenurl = null;
    private String binaryurl = null;


    /**
     * Retourne l'url pour la photo de l'individu dont le cod_ind est plac� en param�tre.
     * @param cod_ind
     * @param cod_etu
     * @return l'url pour r�cup�rer la photo.
     *
     */
    public Lille3Photo() {
        this.imageurl = this.getImageurl();
        this.tokenurl = this.getTokenurl();
        this.binaryurl = this.getBinaryurl();
    }

    public String getImageurl() {
        if (this.imageurl == null) {
                this.imageurl = System.getProperty("context.param.photoserver.imageurl");
        }
        return this.imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTokenurl() {
        if (this.tokenurl == null) {
                this.tokenurl = System.getProperty("context.param.photoserver.tokenurl");
        }
        return this.tokenurl;
    }

    public void setTokenurl(String tokenurl) {
        this.tokenurl = tokenurl;
    }

    public String getBinaryurl() {
        if (this.binaryurl == null) {
                this.binaryurl = System.getProperty("context.param.photoserver.binaryurl");
                }
        return this.binaryurl;
    }

    public void setBinaryurl(String binaryurl) {
        this.binaryurl = binaryurl;
    }


    public String getUrlPhoto(final String cod_ind, String cod_etu, boolean isUtilisateurEnseignant, String loginUser) {
        String tokenUrl = this.tokenurl + cod_etu;
        String token = "";
        try {
                token = this.queryUrl(new URL(tokenUrl));
        } catch(Exception e) {
                token = "";
                LOG.error(e.getMessage());
        }

        return this.imageurl + token;
    }

    /**
     * retourne l'url pour la photo de l'individu dont le cod_ind est plac� en param�tre.
     * Avec une url pour le serveur et non pour l'affichage � l'�cran, pour le client.
     * @param cod_ind
     * @param cod_etu
     * @return l'url pour r�cup�rer la photo
     */
    public String getUrlPhotoTrombinoscopePdf(final String cod_ind, String cod_etu, boolean isUtilisateurEnseignant, String loginUser) {

        return this.binaryurl + cod_etu;
    }

    private String queryUrl(URL url) {

        try {
                StringBuilder result = new StringBuilder();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                return result.toString();
        } catch(Exception e) {
                LOG.error(e.getMessage());
                return "";
        }
    }

    @Override
    public boolean isOperationnel() {
        return true;
    }
}
