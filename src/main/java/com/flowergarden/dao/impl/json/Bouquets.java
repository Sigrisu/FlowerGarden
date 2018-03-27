package com.flowergarden.dao.impl.json;


import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Bouquets {

    @XmlElement(required = true)
    protected List<MarriedBouquet> bouquets;


    public List<MarriedBouquet> getBouquets() {
        if (bouquets == null) {
            bouquets = new ArrayList<MarriedBouquet>();
        }
        return this.bouquets;
    }

    public void setBouquetsFlomList(List<Bouquet> b) {
        bouquets = new ArrayList<>();
        Iterator<Bouquet> bouquetsIterator = b.iterator();
        while (bouquetsIterator.hasNext()) {
            bouquets.add((MarriedBouquet) bouquetsIterator.next());
        }
    }

    public List<Bouquet> getBouquetsToList() {
        List<Bouquet> res = new ArrayList<>();
        res.addAll(bouquets);
        return res;
    }
}