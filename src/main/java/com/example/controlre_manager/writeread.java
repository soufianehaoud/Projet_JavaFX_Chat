package com.example.controlre_manager;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class writeread {
    File fichier;

    public writeread(File fichier) {
        this.fichier = fichier;
    }
    void write(Manager m) throws IOException {
        Manager nj;
        ObjectInputStream entree=null;
        ObjectOutputStream sortie= null;
        boolean flag=false;
        File temporaire=new File("temp.txt");//ou on va écrire
        sortie=new ObjectOutputStream(new FileOutputStream(temporaire));
        try{
            entree=new ObjectInputStream(new FileInputStream(fichier));
            nj=(Manager) entree.readObject();
            while(nj!=null){
                if(m.ID.equals(nj.ID)){
                    sortie.writeObject(m);
                    flag=true;
                }
                else{
                    sortie.writeObject(nj);

                }
                nj=(Manager) entree.readObject();
            }
        }catch(FileNotFoundException e){}
        catch(EOFException e) { entree.close(); }

        catch (ClassNotFoundException e) {

        }
        if(!flag) sortie.writeObject(m);
        sortie.close();
        fichier.delete();
        temporaire.renameTo(fichier);
    }
    Set<Manager> Read() throws IOException {
        Manager nj;
        Set<Manager> s=new HashSet<Manager>();
        ObjectInputStream entree=new ObjectInputStream(new FileInputStream(fichier));
        try{
            nj=(Manager) entree.readObject();
            while(nj!=null){
                s.add(nj);
                nj=(Manager) entree.readObject();
            }
        }catch(EOFException e) {
            entree.close();
        } catch (ClassNotFoundException e) {

        }
        return s;
    }
}
