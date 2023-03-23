/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package view.backing;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.html.HtmlCommandButton;
import jakarta.faces.component.html.HtmlSelectBooleanCheckbox;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import java.util.ResourceBundle;

/**
 *
 * @author kapi1
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    /**
     * Creates a new instance of LoginBean
     */
    private String username;
    private String password;
    private HtmlSelectBooleanCheckbox acceptCheckbox;
    private HtmlCommandButton loginButton;
    public LoginBean() {
    }
    
    public String login() {
        if (username.equals("scott") && password.equals("tiger")) {            
            FacesContext context = FacesContext.getCurrentInstance();              
            ResourceBundle resourceBundle = context.getApplication().getResourceBundle(context, "AppMessages");      
            String messageValue = resourceBundle.getString("validation.oracleError");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageValue, null); 
            context.addMessage(null, message);       
            return "index";
        } else if (username.equals(password)) {
            return "success";
        } else {
            return "failure";
        }
    }
   
    public void activateButton(ValueChangeEvent e)
    {
        if (acceptCheckbox.isSelected())
            loginButton.setDisabled(false);
        else
            loginButton.setDisabled(true);
        FacesContext context = FacesContext.getCurrentInstance();
        context.renderResponse();  
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the acceptCheckbox
     */
    public HtmlSelectBooleanCheckbox getAcceptCheckbox() {
        return acceptCheckbox;
    }

    /**
     * @param acceptCheckbox the acceptCheckbox to set
     */
    public void setAcceptCheckbox(HtmlSelectBooleanCheckbox acceptCheckbox) {
        this.acceptCheckbox = acceptCheckbox;
    }

    /**
     * @return the loginButton
     */
    public HtmlCommandButton getLoginButton() {
        return loginButton;
    }

    /**
     * @param loginButton the loginButton to set
     */
    public void setLoginButton(HtmlCommandButton loginButton) {
        this.loginButton = loginButton;
    }
}
