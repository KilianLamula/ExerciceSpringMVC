/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monprojet.controller;

import java.util.List;
import monprojet.dao.CityRepository;
import monprojet.dao.CountryRepository;
import monprojet.entity.City;
import monprojet.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lamul
 */
@Controller // This means that this class is a Controller
@RequestMapping(path = "/city")
public class CityController {

    // On affichera par défaut la page 'cities.html'
    private static final String DEFAULT_VIEW = "cities";

    //On a besoin du Repository
    @Autowired
    private CityRepository daoCity;
    @Autowired
    private CountryRepository daoCountry;

    @GetMapping(path = "show")
    public String afficheLesVilles(Model model) {
        List<City> cities = daoCity.findAll();
        model.addAttribute("cities", cities);

        List<Country> countries = daoCountry.findAll();
        model.addAttribute("countries", countries);

        return DEFAULT_VIEW;
    }

    @PostMapping(path = "save")
    public String enregistreLaVillePuisMontreLaListe(@RequestParam String name, @RequestParam String population, @RequestParam int country) {

        Country pays = daoCountry.findAllById(country);

        City city = new City(name, pays);
        city.setPopulation(Integer.parseInt(population));
        daoCity.save(city);

        // On re-appelle le contrôleur avec la méthode GET
        return "redirect:show";
    }

}
