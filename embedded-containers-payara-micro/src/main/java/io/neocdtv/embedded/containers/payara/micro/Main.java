/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.embedded.containers.payara.micro;

import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicro;

/**
 *
 * @author wolfkr
 */
public class Main {
    public static void main(String[] args) throws BootstrapException {
        PayaraMicro instance = PayaraMicro.getInstance();
        instance.addDeployment("test.war")
                .bootStrap();
    }
}