/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.models;

/**
 *
 * @author Tamer
 */
public class Page {

    private String name;
    private float hub;
    private float auth;

    public Page(String name, float hub, float auth) {
        this.name = name;
        this.hub = hub;
        this.auth = auth;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hub
     */
    public float getHub() {
        return hub;
    }

    /**
     * @param hub the hub to set
     */
    public void setHub(float hub) {
        this.hub = hub;
    }

    /**
     * @return the auth
     */
    public float getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(float auth) {
        this.auth = auth;
    }

}
