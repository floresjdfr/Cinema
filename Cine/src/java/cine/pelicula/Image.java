/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.pelicula;

import javax.persistence.Column;

/**
 *
 * @author boyro
 */
public class Image {
//
//    private byte[] image;
//
//    @Column(name = "image")
//    public byte[] getImage() {
//        return this.image;
//    }
    private String base64Image;
 
    public String getBase64Image() {
        return base64Image;
    }
 
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
