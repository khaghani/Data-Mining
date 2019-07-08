/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Text;

/**
 *
 * @author amir
 */
public class PhoneEmailFinder {

    private String text;
    private InfoType infoType = InfoType.PhoneAndEmail;

    /////////////////////////////
    public enum InfoType {
        PhoneAndEmail,
        Phone,
        Email
    }

    /////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    public PhoneEmailFinder() {
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /////////////////////////////
    public InfoType getInfoType() {
        return infoType;
    }

    public void setInfoType(InfoType infoType) {
        this.infoType = infoType;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////
    /////////////////////////////
}
