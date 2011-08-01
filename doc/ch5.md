# Problem analysis [Chapter 5]
## Input data characteristics
A database containing records of 73 people was analyzed. That number included 53 patients and 16 healthy persons. Among patients, 31 were undergoing desensitization and 26 were treated conservatively.  
Structure of the database is a two-dimensional matrix with 435 columns. A complete therapy history is coded in one row. Data gathered in fields include: age, patient’s ID number, sex, medical history, medication taken, etc. This data was completely anonymous. The research was conducted in the Pulmonology Ward of the Polish-American Institute of Pediatry at the Collegium Medicum UJ [][#Cichocka-Jarosz:1997].  
Data gathered is partially incomplete. The methodology of filling those gaps is presented further.

After the analysis of parameters used to describe cases and their timelines, parameters were grouped in the following way:

* Interview(wywiad)       mainly initial patient interviews – 14 parameters
* Spring check-up (badania wiosenne)  
    Medical examination performed during spring – 54 parameters, grouped as follows:
    * w1           first year – before the beginning of treatment
    * w2…w7           during following years of treatment
    * wk       during last year of treatment, depending on the length of treatment this data is copied from the w7 or w6 group

* tests (testy)     additional set of tests, the so called specific allergen immunological system reactivity tests  
    14 parameters
    * testy_1           done before the beginning of treatment
    * testy_2  
        done during treatment
    * testy_3           done in the final phase of the treatment
* other  
    containing remaining parameters, including enumerating parameters and those rejected during the first phase of the data analysis

After grouping the parameters, their total number has been reduced to 97. [Table 1][] shows the appearance of the parameters in subsequent groups.

[Table 1 Grouping parameters][Table 1]
| Nazwa | wywiad | w1 | w2 | w3 | w4 | w5 | w6 | w7 | wk | testy_1 | testy_2 | testy_3 | other |
| --- |
| numer_porzadkowy (number) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| na_co_pierwsze_chorowal (patient’s first disease) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| pokwitanie2 (menopause) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| w_czas_choroby_do_it_nos ? | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_czas_choroby_do_it_astma ? | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_eozynofilia (eosinophilia) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| grupa_lub_kontrola (group or control) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| w_eozynofilia_bezw (eosinophilia aboslute) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_ecp | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_eozynofile (eosinophiles) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_eozynofile_zdegranulowane (degranulated eosinophiles) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| grupa_pacjentow (patients group) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_fev | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_swiszczacy_oddech (wheezy breathing) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_kaszel (cough) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_dusznosc (difficult breathing) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| podzial_na_4_grupy (splitting into 4 groups) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| w_swiad_spojowek (conjunctivitis) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_lzawienie_oczu (teary eyes) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_wyciek_z_nosa (runny nose) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_zatkanie_nosa (stuffy nose) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_napady_kichania (sneezing fits) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_wysypki_skorne (rash) | N | N | T | T | T | T | T | N | T | N | N | N | N |
| w_czestosc_infekcji_w_roku (freq. of infections yearly) | N | N | T | T | T | T | T | N | T | N | N | N | N |
| w_hospitalizacja__w_tyg (hospitalization in weeks) | N | N | N | N | N | N | N | N | N | N | N | N | N |
| w_ig_e_swoiste_trawy (specific grass) | N | N | T | N | T | T | T | T | T | N | N | N | N |
| wiek_w_latach (age in years) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_ig_e_swoiste_trawy_klasa (specific grass class) | N | N | T | N | T | T | T | T | T | N | N | N | N |
| w_max_stezenie_szczepionki (max vaccine concentration) | N | N | T | T | T | T | T | N | T | N | N | N | N |
| w_dawka_szczepionki (vaccine dosage) | N | N | T | T | T | T | T | N | T | N | N | N | N |
| w_czas_trwania_objawow (lenght of symptoms occuring) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_czas_trwania_max_objawow (max lenght of symptoms occuring) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| testy_trawy_i (grass tests 1) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| testy_trawy_ii (grass tests 2) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| Rozpoznanie (Diagnosis) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| natezenie_astmy_rozpoznanie (asthma severity diagnosis) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_obciazajacy_wywiad_rodzinny (indicative family history interview) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| obciazenie_rodzinne (inherited disorder) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_procent_zmiennosci_po_beta (percentage of change after beta) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_poprawa_kodowana (coded improvement) | N | N | N | N | N | N | N | N | N | N | N | N | N |
| w_suma_objawow (symptoms summarized) | N | N | T | T | T | T | T | T | N | N | N | N | N |
| w_suma_lekow (medication summarized) | N | N | T | T | T | T | T | T | N | N | N | N | N |
| miesiac_urodzenia (month of birth) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| Plec (Sex) | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_antyhistaminiki (antihistamines) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_metyloksantyny (methylxanthine) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_sterydy_ogolnie (steroids, general) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_sterydy_wziewnie (steroids, inhalated) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_sterydy_donosowo (steroid, nasally administred) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_kromoglikany_wziewnie (cromoglycates, inhaled) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_kromoglikany_donosowo (cromoglycates nasally administred) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_kromoglikany_dospojowkowo (cromoglycates, ocular delivery) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_beta-mimetyki (beta-mimetics) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| w_betadrin_miejscowo (betadrine, locally) | N | N | T | T | T | T | T | T | T | N | N | N | N |
| testy_drzewa (tests – trees) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| testy_chwasty (tests – weeds) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| testy_plesnie (tests – molds) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| testy_kurz (tests – dust) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| testy_dermpt (tests demfa) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| testy_dermfa (tests demfa) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| ide_c | N | N | N | N | N | N | N | N | N | T | T | T | N |
| ige_swtr | N | N | N | N | N | N | N | N | N | T | T | T | N |
| ige_swtr_klasa | N | N | N | N | N | N | N | N | N | T | T | T | N |
| w_czas_choroby_do_it_skora lenght of sickness – skin | T | N | N | N | N | N | N | N | N | N | N | N | N |
| ige_calk_ponad_norme (ige total over the norm) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| ige_swtr_kl_wieksze_rowne_2 (ige swtr higher or equal to 2) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| odczulani_przez_4_lub_5_lat (desensitized through 4 or  years) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| igec_lub_igesw (igec or igesw) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| igesw_lub_igec | N | N | N | N | N | N | N | N | N | T | T | T | N |
| procent_poprawa (percent – improvement) | N | N | T | T | T | T | N | N | N | N | N | N | N |
| kryt_igec_800  | N | N | N | N | N | N | N | N | N | N | N | N | T |
| kryt_igesw_3  | N | N | N | N | N | N | N | N | N | N | N | N | T |
| kryt_eobzw_700  | N | N | N | N | N | N | N | N | N | N | N | N | T |
| w_dlugosc_choroby_przed_it lenght of sicknes before it | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_phadiatop | T | N | N | N | N | N | N | N | N | N | N | N | N |
| w_leukocytoza (leukocitosis) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_liczba_pol_naplywowych | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_liczba_pol_nablonkowych epithelium count | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_neutrofilenutrophiles | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_limfocyty lymphocytes | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_komorki_kubkowe (taste receptor cells) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_komorki_przypodstawne (basal cells) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_sposob_zluszczania (form of scaling) | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_fef_5-75 | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_pefr | N | T | T | T | T | T | T | T | T | N | N | N | N |
| w_roznica_miedzy_objawami (difference between symptoms) | N | N | N | N | N | N | N | N | N | N | N | N | N |
| w_ig_e_calkowite (total ige) | N | N | T | N | T | T | T | T | T | N | N | N | N |
| w_reakcje_poszczepienne (vaccine reactions) | N | N | T | T | T | T | T | N | T | N | N | N | N |
| w_hospitalizacja_tyg (hospitalization in weeks) | N | N | N | N | T | T | T | N | T | N | N | N | N |
| Pokwitanie (Menopause) | N | N | N | N | N | N | N | N | N | N | N | N | T |
| w_procent_zmiennoci_po_beta (percentage of change after beta) | N | N | N | N | N | N | N | N | N | N | N | N | N |
| kontrola_dodatnia (positice control) | N | N | N | N | N | N | N | N | N | T | T | T | N |
| procent_rozn_sumy_lekow (percentage of total medication amount difference) | N | N | T | T | T | T | N | N | N | N | N | N | N |
| Poprawa (Improvement) | N | N | T | T | T | T | N | N | N | N | N | N | N |
| roznica_sumy_objawow (difference of the sum of symptoms) | N | N | T | T | T | T | N | N | N | N | N | N | N |
| procent_rozn_sumy_objawow (percentage of the the difference of the symtoms sum) | N | N | T | T | T | T | N | N | N | N | N | N | N |
| rozn_sumy_lekow (total amount of medication difference)  | N | N | T | T | T | T | N | N | N | N | N | N | N |

## Description and documentation of the analysis conducted
An analysis described later is based on the already existing statistical research. An expert was interviewed during the first stage. Then, the data was preliminarily processed in order to make it compatible with the requirements of artificial neural networks. The final stage was devoted to the interpretation of the results. 

### Initial data filtering with a help of an expert
Influence of different factors on immunotherapy’s effectiveness is discussed in existing literature [][#Alergologia:1998]. There are hypotheses claiming that a correlation occurs between susceptibility to allergies and the month of birth. It was therefore desirable to begin the research by conducting a complete analysis of the parameters with an expert. Dr. Ewa Cichocka-Jarosz, specializing in desensitizing children [][#Cichocka-Jarosz:1997], was consulted. In effect a priority level had been assigned to every parameter, reflecting its influence on the desensitization process. Numbers 1-10 were used for this purpose. The lower the value, the bigger the factor’s impact. This approach allowed for future manipulations of the dataset. Changing of a single variable caused the parameter set to either grow or shrink [Appendix A][]. [Table 2][] contains the priority scores assigned. 

[Table 2 Parameters’ priorities][Table 2]
| Nazwa | p |
| --- |
| numer_porzadkowy | 1 |
| na_co_pierwsze_chorowal | 1 |
| pokwitanie2 | 1 |
| w_czas_choroby_do_it_nos | 1 |
| w_czas_choroby_do_it_astma | 1 |
| w_eozynofilia | 1 |
| grupa_lub_kontrola | 1 |
| w_eozynofilia_bezw | 1 |
| w_ecp | 1 |
| w_eozynofile | 1 |
| w_eozynofile_zdegranulowane | 1 |
| grupa_pacjentow | 1 |
| w_fev | 1 |
| w_swiszczacy_oddech | 1 |
| w_kaszel | 1 |
| w_dusznosc | 1 |
| podzial_na_4_grupy | 1 |
| w_swiad_spojowek | 1 |
| w_lzawienie_oczu | 1 |
| w_wyciek_z_nosa | 1 |
| w_zatkanie_nosa | 1 |
| w_napady_kichania | 1 |
| w_wysypki_skorne | 1 |
| w_czestosc_infekcji_w_roku | 1 |
| w_hospitalizacja__w_tyg | 1 |
| w_ig_e_swoiste_trawy | 1 |
| wiek_w_latach | 1 |
| w_ig_e_swoiste_trawy_klasa | 1 |
| w_max_stezenie_szczepionki | 1 |
| w_dawka_szczepionki | 1 |
| w_czas_trwania_objawow | 1 |
| w_czas_trwania_max_objawow | 1 |
| testy_trawy_i | 1 |
| testy_trawy_ii | 1 |
| rozpoznanie | 1 |
| natezenie_astmy_rozpoznanie | 1 |
| w_obciazajacy_wywiad_rodzinny | 2 |
| obciazenie_rodzinne | 2 |
| w_procent_zmiennosci_po_beta | 2 |
| w_poprawa_kodowana | 2 |
| w_suma_objawow | 2 |
| w_suma_lekow | 2 |
| miesiac_urodzenia | 3 |
| plec | 4 |
| w_antyhistaminiki | 5 |
| w_metyloksantyny | 5 |
| w_sterydy_ogolnie | 5 |
| w_sterydy_wziewnie | 5 |
| w_sterydy_donosowo | 5 |
| w_kromoglikany_wziewnie | 5 |
| w_kromoglikany_donosowo | 5 |
| w_kromoglikany_dospojowkowo | 5 |
| w_beta-mimetyki | 5 |
| w_betadrin_miejscowo | 5 |
| testy_drzewa | 9 |
| testy_chwasty | 9 |
| testy_plesnie | 9 |
| testy_kurz | 9 |
| testy_dermpt | 9 |
| testy_dermfa | 9 |
| ide_c | 10 |
| ige_swtr | 10 |
| ige_swtr_klasa | 10 |
| w_czas_choroby_do_it_skora | 10 |
| ige_calk_ponad_norme | 10 |
| ige_swtr_kl_wieksze_rowne_2 | 10 |
| odczulani_przez_4_lub_5_lat | 10 |
| igec_lub_igesw | 10 |
| igesw_lub_igec | 10 |
| procent_poprawa | 10 |
| kryt_igec_800 | 10 |
| kryt_igesw_3 | 10 |
| kryt_eobzw_700 | 10 |
| w_dlugosc_choroby_przed_it | 10 |
| w_phadiatop | 10 |
| w_leukocytoza | 10 |
| w_liczba_pol_naplywowych | 10 |
| w_liczba_pol_nablonkowych | 10 |
| w_neutrofile | 10 |
| w_limfocyty | 10 |
| w_komorki_kubkowe | 10 |
| w_komorki_przypodstawne | 10 |
| w_sposob_zluszczania | 10 |
| w_fef_5-75 | 10 |
| w_pefr | 10 |
| w_roznica_miedzy_objawami | 10 |
| w_ig_e_calkowite | 10 |
| w_reakcje_poszczepienne | 10 |
| w_hospitalizacja_tyg | 10 |
| Pokwitanie | 10 |
| w_procent_zmiennoci_po_beta | 10 |
| kontrola_dodatnia | 10 |
| Procent_rozn_sumy_lekow | 11 |
| Poprawa | 11 |
| Roznica_sumy_objawow | 11 |
| Procent_rozn_sumy_objawow | 11 |
| rozn_sumy_lekow | 11 |

### Supplementary data analysis
Since the original data was gathered by working with offsite patients, it was partially incomplete. Missing data was interpolated by utilizing a second-order spline. This method had been selected due to its speed and valid results for the real world data. [Figure 9][] demonstrates the interpolation and extrapolation of data gaps:

![Figure 9](fig9.pdf)
Figure 9 Example of data gap-filling  
blue – measured values  
red – interpolated values  
green – extrapolated values

Of the three parameters describing menopause, one was selected as a binary variable – “pokwitanie2” (menopause2).
Additional parameters were calculated:
* For the spring check-ups
    * Suma_lekow_diff       difference between the medication taken in current year and previous year
    * Suma_lekow_mul       quotient of medication taken in current year and previous year
    * suma_lekow_dic_2…32       sum of medications divided by subsequent powers of 2       for w3 divisor=2  
    for w4 divisor=4  
    …  
    for wk divisor = 32  
    So the longer the treatment the less important the parameter value becomes compared with other parameters
* global       based on deterioration, i.e. the increase in amount of medication administered to patients compared with previous year
    * stosunek_pogorsz (deterioration)  
    relation of deterioration level to the length of treatment  
    this means that if a patient was treated for 5 years and had one deterioration period the parameter = 0.2
    * stopien_pogorszenia_max       maximum deterioration  
    maximum value of quotient of sums of medication amount throughout the whole period of treatment  
    When preparing data for normalization, parameters value ranges and types were defined:

[Table 3 Parameters value ranges and types][Table 3]
| Name | type | min | Max |
| --- |
| numer_porzadkowy | int | 1 | 80 |
| na_co_pierwsze_chorowal | int | 0 | 3 |
| pokwitanie2 | int | 0 | 7 |
| w_czas_choroby_do_it_nos | int | 2 | 10 |
| w_czas_choroby_do_it_astma | int | 0 | 10 |
| w_eozynofilia | int | 0 | 22 |
| grupa_lub_kontrola | int | 1 | 2 |
| w_eozynofilia_bezw | int | 0 | 2730 |
| w_ecp | double | 3.04 | 146.7 |
| w_eozynofile | int | 0 | 98 |
| w_eozynofile_zdegranulowane | int | 0 | 5 |
| grupa_pacjentow | int | 1 | 3 |
| w_fev | int | 0 | 161 |
| w_swiszczacy_oddech | int | 0 | 3 |
| w_kaszel | int | 0 | 7 |
| w_dusznosc | int | 0 | 3 |
| podzial_na_4_grupy | int | 1 | 4 |
| w_swiad_spojowek | int | 0 | 3 |
| w_lzawienie_oczu | int | 0 | 3 |
| w_wyciek_z_nosa | int | 0 | 3 |
| w_zatkanie_nosa | int | 0 | 3 |
| w_napady_kichania | int | 0 | 3 |
| w_wysypki_skorne | int | 0 | 3 |
| w_czestosc_infekcji_w_roku | int | 0 | 20 |
| w_hospitalizacja__w_tyg |  |  |  |
| w_ig_e_swoiste_trawy | double | 0.8 | 157.9 |
| wiek_w_latach | int | 5 | 15 |
| w_ig_e_swoiste_trawy_klasa | int | 2 | 5 |
| w_max_stezenie_szczepionki | int | 250 | 10000 |
| w_dawka_szczepionki | int | 25 | 10000 |
| w_czas_trwania_objawow | int | 1 | 149 |
| w_czas_trwania_max_objawow | int | 1 | 113 |
| testy_trawy_i | int | 1 | 5 |
| testy_trawy_ii | int | 1 | 5 |
| rozpoznanie | int | 1 | 10 |
| natezenie_astmy_rozpoznanie | int | 1 | 3 |
| w_obciazajacy_wywiad_rodzinny | int | 1 | 10 |
| obciazenie_rodzinne | int | 0 | 2 |
| w_procent_zmiennosci_po_beta | double | -22.0 | 92.0 |
| w_poprawa_kodowana |  |  |  |
| w_suma_objawow | int | 0 | 27 |
| w_suma_lekow | int | 0 | 28 |
| miesiac_urodzenia | int | 1 | 12 |
| plec | int | 1 | 2 |
| w_antyhistaminiki | int | 0 | 6 |
| w_metyloksantyny | int | 0 | 3 |
| w_sterydy_ogolnie | int | 0 | 2 |
| w_sterydy_wziewnie | int | 0 | 3 |
| w_sterydy_donosowo | int | 0 | 3 |
| w_kromoglikany_wziewnie | int | 0 | 3 |
| w_kromoglikany_donosowo | int | 0 | 3 |
| w_kromoglikany_dospojowkowo | int | 0 | 3 |
| w_beta-mimetyki | int | 0 | 3 |
| w_betadrin_miejscowo | int | 0 | 3 |
| testy_drzewa | int | 1 | 5 |
| testy_chwasty | int | 1 | 5 |
| testy_plesnie | int | 1 | 4 |
| testy_kurz | int | 1 | 5 |
| testy_dermpt | int | 1 | 5 |
| testy_dermfa | int | 1 | 5 |
| ide_c | double | 11.6 | 2095.0 |
| ige_swtr | double | 0.8 | 52.0 |
| ige_swtr_klasa | int | 2 | 5 |
| w_czas_choroby_do_it_skora | int | 1 | 8 |
| ige_calk_ponad_norme | int | 0 | 2 |
| ige_swtr_kl_wieksze_rowne_2 | int | 0 | 2 |
| odczulani_przez_4_lub_5_lat | int | 1 | 2 |
| igec_lub_igesw | double | 1.067901 | 812.086331 |
| igesw_lub_igec | double | 0.001231 | 0.936416 |
| procent_poprawa | int | 0 | 2 |
| kryt_igec_800 | int | 0 | 2 |
| kryt_igesw_3 | int | 0 | 2 |
| kryt_eobzw_700 | int | 1 | 1 |
| w_dlugosc_choroby_przed_it | int | 2 | 10 |
| w_phadiatop | int | 1 | 2 |
| w_leukocytoza | int | 3400 | 20300 |
| w_liczba_pol_naplywowych | int | 0 | 60 |
| w_liczba_pol_nablonkowych | int | 0 | 55 |
| w_neutrofile | int | 0 | 98 |
| w_limfocyty | int | 0 | 47 |
| w_komorki_kubkowe | int | 0 | 59 |
| w_komorki_przypodstawne | int | 0 | 57 |
| w_sposob_zluszczania | int | 1 | 3 |
| w_fef_5-75 | int | 0 | 206 |
| w_pefr | int | 0 | 200 |
| w_roznica_miedzy_objawami |  |  |  |
| w_ig_e_calkowite | double | 11.6 | 2095.0 |
| w_reakcje_poszczepienne | int | 1 | 1 |
| w_hospitalizacja_tyg | int | 2 | 2 |
| pokwitanie | int | 90 | 96 |
| w_procent_zmiennoci_po_beta |  |  |  |
| kontrola_dodatnia | int | 2 | 20 |
| procent_rozn_sumy_lekow | double | -320.0 | 100.0 |
| Poprawa | int | 1 | 2 |
| roznica_sumy_objawow | int | -10 | 23 |
| procent_rozn_sumy_objawow | double | -167.0 | 100.0 |
| rozn_sumy_lekow | int | -18 | 22 |

### Discussion on the network selection
Capabilities and effectiveness of neural networks depend on their types and topologies. Self-organizing networks have no application to problems with known expected results. Supervised networks come in several variants. The problem in question is finding a network predicting a single continuous parameter based on the input data that is compromised due to a patient’s subjective testimony. 
**Linear networks** – they divide the space with hyperplanes. They can classify input data as linear discriminating functions, or, for the regressing problems, create a plane common for all results. For the single dimension input vector it will be vector of the length similar to an arithmetic mean of the expected outputs set. In our case this variant has no application, since single output required is represented with a more complex function than a linear function. 
**Multi Layer Perceptron (MLP) networks** – they are the most flexible type of neural networks, thanks to the flexibility of their structures, the number of layers and the number of neurons in layers. The price for this flexibility is the long time needed for training and the complex method of deciding upon their structure. Networks of this type are compatible with data containing measurement errors. Potentially, they may be a good fit for the problem defined.  
Bayesian networks (Probabilistic Neural Networks and General Recurrent Neural Networks) – they are not very useful without an additional input data filtering to distinguish classes in place of continuous values. Moreover, these types of networks require large and representative input sets. Unfortunately, the available set is comprised of only 31 treated patients.      
   
In order to choose the right type and the initial topology of a network, STATISTICA Neural Networks software suite was used. Its heuristic network architecture modeling capability was utilized. It is a module based on genetic algorithms. The smallest testing error with maintaining small validation error was achieved with MLP networks containing 3 hidden layers. Therefore, the SNN application heuristic analysis confirmed that the MLP type would be the most appropriate choice for the problem in question.  
The research was conducted using a network trained with data coming from the “interview” and the results of tests ran during the first year of treatment – “w1”. Deterioration_level_max (stopien_pogorszenia_max) was the expected value. This parameter provided satisfactory measure of patients’ responsiveness to treatment. It was approved by the expert as a sufficient premise calculated by the system based on patient’s data.  
In order to maintain the same grouping for the training, validation and testing sets in subsequent trails, grouping was coded in additional field as “elements_category” (kategoria_elementu). Small training set – 32 patients undergoing desensitization – significantly limited the size of subsets, and therefore their representativity.  
When training a network, an effort is made to minimize the training set error, so that the validating set error would also demonstrate this tendency[^foot-deltarule]. The training is interrupted or modified, when the validating set error increases despite the training set error decreasing. This phenomenon signals the network’s over-training. Additionally, when a system is also manipulating the architecture and additional testing set is needed to compare the effectiveness of architectures employed.  
Mean square error is difficult to interpret in effectiveness and efficacy terms Therefore, a measure of quality is used.  
Quality is defined as standard deviation of prediction errors divided by the standard deviation of the output values. The lower the value, the better the network is. 1 is the reference value, meaning that standard deviations of the errors and output values are equal. Further discussion on quality can be found in Chapter “5.4.2. Results interpretation – quality of network”. 

[Table 4 Model comparison report  – SNN analysis  (for “interview” and “ w1”)][Table 4]
| Type | Quality u | Quality w | Quality t | Error u | Error w | Error t | Training |
| --- |
| MLP 12:12-20-14-1:1 | 0,380690 | 0,498705 | 1,190093 | 0,121202 | 0,094067 | 0,279640 | BP100,CG20,CG0b |
| MLP 14:16-20-9-1:1 | 0,335765 | 0,607185 | 1,284842 | 0,150175 | 0,111574 | 0,299184 | BP100,CG20,CG0b |
| MLP 14:16-20-14-1:1 | 0,582792 | 0,437756 | 1,116657 | 0,237672 | 0,105682 | 0,319769 | BP100,CG20,CG0b |
| MLP 13:15-20-12-1:1 | 0,123424 | 0,389140 | 1,436121 | 0,035087 | 0,096008 | 0,319984 | BP100,CG20,CG4b |
| MLP 7:7-6-7-1:1 | 0,665690 | 0,610414 | 1,447535 | 0,179719 | 0,116275 | 0,330673 | BP100,CG20,CG0b |
| MLP 15:17-20-9-1:1 | 0,264581 | 0,618745 | 1,451452 | 0,138155 | 0,114406 | 0,338800 | BP100,CG20,CG0b |
| MLP 15:17-10-1:1 | 0,231660 | 0,454477 | 1,200153 | 0,078188 | 0,109531 | 0,339726 | BP100,CG20,CG4b |
| MLP 4:4-2-1:1 | 0,805073 | 0,552974 | 1,484017 | 0,219297 | 0,101591 | 0,344319 | BP100,CG20,CG0b |
| MLP 4:4-3-1:1 | 0,811213 | 0,557543 | 1,456236 | 0,219032 | 0,104597 | 0,345654 | BP100,CG20,CG0b |
| MLP 15:17-11-1:1 | 0,198413 | 0,540841 | 1,536433 | 0,104214 | 0,105661 | 0,349663 | BP100,CG20,CG0b |
| MLP 4:4-2-1:1 | 0,780863 | 0,526773 | 1,507607 | 0,213295 | 0,096767 | 0,350154 | BP100,CG20,CG0b |
| MLP 4:4-4-3-1:1 | 0,846051 | 0,552078 | 1,511738 | 0,229329 | 0,101746 | 0,351926 | BP100,CG20,CG0b |
| MLP 5:5-5-1:1 | 0,710321 | 0,590508 | 1,579424 | 0,201146 | 0,108658 | 0,356409 | BP100,CG20,CG0b |
| MLP 4:4-2-1:1 | 0,774417 | 0,513943 | 1,471086 | 0,210242 | 0,111574 | 0,359952 | BP100,CG20,CG0b |
| MLP 12:14-17-11-1:1 | 0,162781 | 0,434490 | 1,639542 | 0,044071 | 0,110262 | 0,362816 | BP100,CG20,CG7b |
| MLP 5:5-3-1:1 | 0,745334 | 0,538176 | 1,611793 | 0,209919 | 0,101412 | 0,363514 | BP100,CG20,CG0b |
| MLP 11:13-12-1:1 | 0,260644 | 0,384830 | 1,004149 | 0,088035 | 0,097189 | 0,365674 | BP100,CG20,CG4b |
| MLP 9:11-9-3-1:1 | 0,577114 | 0,623746 | 1,628581 | 0,156657 | 0,116341 | 0,367229 | BP100,CG20,CG0b |
| MLP 16:18-19-11-1:1 | 0,350160 | 0,578795 | 1,654540 | 0,119055 | 0,112475 | 0,369242 | BP100,CG20,CG0b |
| MLP 6:6-6-1:1 | 0,725854 | 0,615156 | 1,571625 | 0,196061 | 0,115624 | 0,370892 | BP100,CG20,CG0b |
| MLP 4:4-2-4-1:1 | 0,704872 | 0,556447 | 1,585524 | 0,190951 | 0,116872 | 0,371313 | BP100,CG20,CG2b |
| MLP 16:18-20-9-1:1 | 0,370024 | 0,542554 | 1,640938 | 0,182768 | 0,103323 | 0,372596 | BP100,CG20,CG0b |
| MLP 6:6-6-1:1 | 0,917078 | 0,503426 | 1,327563 | 0,309476 | 0,115617 | 0,372698 | BP100,CG20,CG0b |
| MLP 4:4-1-1:1 | 0,777665 | 0,426751 | 1,554062 | 0,210117 | 0,090829 | 0,373355 | BP100,CG20,CG0b |
| MLP 5:5-5-1:1 | 0,771043 | 0,535800 | 1,659822 | 0,211810 | 0,104675 | 0,380664 | BP100,CG20,CG0b |
| MLP 13:15-10-1:1 | 0,321735 | 0,546476 | 1,728790 | 0,104882 | 0,115704 | 0,381954 | BP100,CG20,CG0b |
| MLP 11:11-19-12-1:1 | 0,590113 | 0,554898 | 1,629349 | 0,159363 | 0,110727 | 0,386118 | BP100,CG20,CG0b |
| MLP 4:4-2-3-1:1 | 0,781493 | 0,465929 | 1,636549 | 0,211054 | 0,085820 | 0,391814 | BP100,CG20,CG0b |
| MLP 15:17-20-13-1:1 | 0,211603 | 0,445799 | 1,783640 | 0,096922 | 0,115727 | 0,394946 | BP100,CG20,CG0b |
| MLP 13:15-13-1:1 | 0,644430 | 0,377362 | 1,417624 | 0,217484 | 0,089714 | 0,396624 | BP100,CG20,CG3b |
| MLP 13:13-20-12-1:1 | 0,031235 | 0,335306 | 0,960763 | 0,010541 | 0,112789 | 0,398376 | BP100,CG3c,CG18b |
| MLP 8:10-9-1:1 | 0,477281 | 0,530189 | 1,811746 | 0,132172 | 0,114050 | 0,402044 | BP100,CG20,CG0b |
| MLP 15:17-20-11-1:1 | 0,402919 | 0,482174 | 1,380107 | 0,136005 | 0,111542 | 0,404736 | BP100,CG20,CG0b |
| MLP 16:18-20-14-1:1 | 0,107097 | 0,602723 | 1,866231 | 0,039450 | 0,112048 | 0,412420 | BP100,CG18c,CG0b |
| MLP 16:18-20-16-1:1 | 0,192825 | 0,352507 | 1,899029 | 0,054619 | 0,069533 | 0,419564 | BP100,CG20,CG0b |
| MLP 4:4-4-2-1:1 | 0,823711 | 0,416346 | 1,741773 | 0,222495 | 0,077512 | 0,426589 | BP100,CG20,CG0b |
| MLP 6:8-4-1:1 | 0,734464 | 0,435643 | 1,564672 | 0,254432 | 0,101011 | 0,433064 | BP100,CG20,CG0b |
| MLP 9:9-16-1:1 | 0,169805 | 0,582778 | 1,977887 | 0,045926 | 0,114559 | 0,437340 | BP100,CG20,CG19b |
| MLP 10:12-9-1:1 | 0,716975 | 0,467183 | 1,650398 | 0,242616 | 0,109974 | 0,457386 | BP100,CG10c,CG1b |
| MLP 13:15-15-1:1 | 0,162015 | 0,590399 | 2,083176 | 0,044870 | 0,116668 | 0,461158 | BP100,CG20,CG1b |
| MLP 14:16-20-12-1:1 | 0,914459 | 0,432743 | 1,704218 | 0,382589 | 0,113037 | 0,474796 | BP100,CG20,CG0b |
| MLP 12:14-8-1:1 | 0,806643 | 0,458358 | 1,473552 | 0,410181 | 0,110501 | 0,481491 | BP100,CG20,CG0b |
| MLP 12:14-20-7-1:1 | 0,883554 | 0,439167 | 1,869964 | 0,311153 | 0,101850 | 0,522187 | BP100,CG20,CG0b |
| MLP 7:7-8-1:1 | 0,978610 | 0,427022 | 1,853650 | 0,330255 | 0,100256 | 0,532399 | BP100,CG20,CG0b |
| MLP 12:14-12-1:1 | 0,031931 | 0,445699 | 1,970777 | 0,010837 | 0,113703 | 0,547220 | BP100,CG9c,CG38b |
| MLP 16:18-20-12-1:1 | 0,993051 | 0,413447 | 2,153515 | 0,335482 | 0,110189 | 0,594736 | BP100,CG20,CG0b |
| MLP 12:12-20-11-1:1 | 0,992333 | 0,404479 | 1,933073 | 0,446052 | 0,102372 | 0,613625 | BP100,CG2c,CG0b |
| MLP 12:14-18-2-1:1 | 0,015102 | 0,295528 | 2,372166 | 0,005203 | 0,090217 | 0,655342 | BP100,CG20,CG111b |
| MLP 11:13-11-1:1 | 0,980947 | 0,502983 | 2,596820 | 0,331149 | 0,115505 | 0,722754 | BP100,CG20,CG0b |
| MLP 14:16-12-1:1 | 0,740250 | 0,362849 | 3,830122 | 0,249813 | 0,102414 | 1,062333 | BP100,CG19c,CG0b |

[Table 4][] Notes: Quality u is the prediction quality for the training set. Similarly, ‘w’ i ’t’ qualities are the validating set quality and the testing set quality. The lower the value the better network predicting for the given set. ’t’ set is used to compare architectures.

Two networks are highlighted in the table. First one has the smallest error for the training set. The second one has the lowest quality factor for this set.

![Figure 10](fig10.pdf)
Figure 10 Structure with the lowest error and the best quality for the training set. MLP 14:16-20-14-1:1 stands for Multiple Layers Perceptron with 14 inputs, one output and two hidden layers. 

As a test, an additional analysis was performed, with the data set expanded by the patients’ results during the second year of treatment – “w2”. Errors for validating and testing sets were smaller. At the same time the selected network also had better quality than the previous one. Unfortunately from the point of view of the patient the prognosis given only after having results of two year therapy is not very valuable. Further work was meant to improve the prediction accuracy for the network which only uses data from the interview and the initial check-ups results.

[Table 5 Model comparison report – SNN analysis (for the interview, w1 and w2)][Table 5]
| Type | Quality u | Quality w | Quality t | Error u | Error w | Error t | Training |
| --- |
| MLP 38:40-40-26-1:1 | 0,836195 | 0,465419 | 1,627109 | 0,250976 | 0,086959 | 0,394338 | BP100,CG20,CG0b |
| MLP 12:12-6-7-1:1 | 0,388333 | 0,466980 | 2,281773 | 0,105675 | 0,086839 | 0,536553 | BP100,CG20,CG4b |
| MLP 35:37-40-6-1:1 | 0,709306 | 0,363941 | 1,118662 | 0,239400 | 0,086805 | 0,308959 | BP100,CG20,CG1b |
| MLP 34:36-40-20-1:1 | 0,971769 | 0,460347 | 2,121890 | 0,290635 | 0,085301 | 0,573053 | BP100,CG20,CG0b |
| MLP 39:41-40-28-1:1 | 0,117491 | 0,422299 | 1,393216 | 0,032230 | 0,084823 | 0,340834 | BP100,CG20,CG7b |
| MLP 37:39-19-1:1 | 0,601358 | 0,458186 | 1,809214 | 0,164106 | 0,084525 | 0,469893 | BP100,CG20,CG0b |
| MLP 17:19-9-1:1 | 0,635945 | 0,244268 | 1,493312 | 0,214677 | 0,084072 | 0,414894 | BP100,CG20,CG1b |
| MLP 18:18-34-19-1:1 | 0,192059 | 0,425279 | 2,114409 | 0,052361 | 0,083869 | 0,471900 | BP100,CG20,CG5b |
| MLP 29:31-26-1:1 | 0,085060 | 0,405942 | 1,803581 | 0,023062 | 0,082126 | 0,404179 | BP100,CG20,CG12b |
| MLP 34:36-17-1:1 | 0,473714 | 0,335118 | 1,725211 | 0,128147 | 0,082008 | 0,394739 | BP100,CG20,CG0b |
| MLP 29:29-40-18-1:1 | 0,078984 | 0,448008 | 2,350837 | 0,022681 | 0,081633 | 0,605806 | BP100,CG20,CG18b |
| MLP 16:16-9-11-1:1 | 0,354839 | 0,432317 | 2,009292 | 0,109127 | 0,081372 | 0,444116 | BP100,CG20,CG0b |
| MLP 14:14-17-1:1 | 0,459533 | 0,453861 | 1,391488 | 0,124181 | 0,081017 | 0,336539 | BP100,CG20,CG4b |
| MLP 34:36-25-1:1 | 0,036789 | 0,405598 | 1,684664 | 0,010378 | 0,080602 | 0,382766 | BP100,CG20,CG28b |
| MLP 39:41-27-1:1 | 0,065593 | 0,292724 | 1,518838 | 0,017757 | 0,080538 | 0,339172 | BP100,CG20,CG29b |
| MLP 33:35-40-16-1:1 | 0,328557 | 0,417086 | 1,080160 | 0,089776 | 0,080095 | 0,284576 | BP100,CG20,CG4b |
| MLP 23:25-19-1:1 | 0,464134 | 0,406869 | 1,995531 | 0,125523 | 0,079509 | 0,460588 | BP100,CG20,CG1b |
| MLP 14:14-5-1:1 | 0,894167 | 0,198910 | 1,607734 | 0,301950 | 0,079314 | 0,481368 | BP100,CG20,CG0b |
| MLP 32:34-40-8-1:1 | 0,601268 | 0,339794 | 1,116162 | 0,216758 | 0,079232 | 0,362675 | BP100,CG20,CG1b |
| MLP 39:41-40-16-1:1 | 0,629805 | 0,374169 | 0,690159 | 0,171326 | 0,079172 | 0,184777 | BP100,CG20,CG10b |
| MLP 13:15-37-12-1:1 | 0,447983 | 0,334896 | 2,023599 | 0,151498 | 0,077608 | 0,609311 | BP100,CG20,CG20b |
| MLP 24:24-8-1:1 | 0,469401 | 0,299433 | 1,498087 | 0,161663 | 0,076724 | 0,452462 | BP100,CG20,CG1b |
| MLP 34:36-15-1:1 | 0,059134 | 0,406987 | 1,237139 | 0,016843 | 0,076307 | 0,283994 | BP100,CG20,CG10b |
| MLP 40:42-40-23-1:1 | 0,815075 | 0,414543 | 1,185614 | 0,223124 | 0,076161 | 0,296184 | BP100,CG20,CG0b |
| MLP 35:37-23-1:1 | 0,529082 | 0,346977 | 2,481005 | 0,144451 | 0,075021 | 0,622625 | BP100,CG20,CG0b |
| MLP 30:32-40-10-1:1 | 0,951601 | 0,307600 | 1,099561 | 0,321158 | 0,073996 | 0,307148 | BP100,CG20,CG0b |
| MLP 37:39-40-18-1:1 | 0,134649 | 0,411785 | 1,659781 | 0,043799 | 0,073666 | 0,385314 | BP100,CG20,CG2b |
| MLP 16:18-9-1:1 | 0,837666 | 0,314034 | 1,224892 | 0,282964 | 0,072827 | 0,347743 | BP100,CG20,CG0b |
| MLP 34:34-40-15-1:1 | 0,680716 | 0,393129 | 1,546247 | 0,191123 | 0,071456 | 0,345588 | BP100,CG20,CG0b |
| MLP 40:42-31-1:1 | 0,153255 | 0,393670 | 1,612541 | 0,042144 | 0,071454 | 0,365720 | BP100,CG20,CG3b |
| MLP 34:36-24-1:1 | 0,553121 | 0,346357 | 1,808529 | 0,163637 | 0,069924 | 0,399824 | BP100,CG20,CG0b |
| MLP 37:39-22-1:1 | 0,115546 | 0,384483 | 1,957599 | 0,031316 | 0,069131 | 0,437232 | BP100,CG20,CG6b |
| MLP 35:37-23-1:1 | 0,097334 | 0,383827 | 1,539772 | 0,026497 | 0,068702 | 0,344871 | BP100,CG20,CG16b |
| MLP 6:6-6-11-1:1 | 0,743648 | 0,377724 | 1,296814 | 0,235087 | 0,068229 | 0,286671 | BP100,CG20,CG0b |
| MLP 35:37-40-15-1:1 | 0,033573 | 0,296309 | 1,502666 | 0,009432 | 0,067752 | 0,340602 | BP100,CG20,CG12b |
| MLP 26:26-38-19-1:1 | 0,345694 | 0,325209 | 1,582741 | 0,094624 | 0,066279 | 0,356308 | BP100,CG20,CG3b |
| MLP 36:38-25-1:1 | 0,443914 | 0,290156 | 1,258487 | 0,150887 | 0,065256 | 0,353508 | BP100,CG7c,CG1b |
| MLP 25:27-9-1:1 | 0,457702 | 0,284045 | 2,034409 | 0,155510 | 0,064125 | 0,608277 | BP100,CG20,CG1b |
| MLP 39:41-40-25-1:1 | 0,870786 | 0,305177 | 0,995518 | 0,239157 | 0,063939 | 0,249343 | BP100,CG20,CG0b |
| MLP 22:24-20-1:1 | 0,714044 | 0,176330 | 1,806060 | 0,193043 | 0,063709 | 0,428589 | BP100,CG20,CG0b |
| MLP 33:35-26-1:1 | 0,197774 | 0,336516 | 1,387059 | 0,053400 | 0,062835 | 0,308088 | BP100,CG20,CG13b |
| MLP 18:20-38-19-1:1 | 0,501786 | 0,261710 | 1,833628 | 0,171049 | 0,059422 | 0,558767 | BP100,CG20,CG3b |
| MLP 36:38-40-23-1:1 | 0,271958 | 0,308329 | 2,095973 | 0,080677 | 0,058506 | 0,486857 | BP100,CG20,CG2b |
| MLP 33:35-31-1:1 | 0,007210 | 0,282025 | 1,238878 | 0,001948 | 0,050318 | 0,275998 | BP100,CG20,CG21b |
| MLP 40:42-34-1:1 | 0,157288 | 0,257830 | 1,341501 | 0,042499 | 0,049886 | 0,307522 | BP100,CG20c,CG0b |
| MLP 36:38-40-16-1:1 | 0,178566 | 0,248018 | 1,494768 | 0,049189 | 0,045384 | 0,330321 | BP100,CG20,CG2b |
| MLP 36:38-28-1:1 | 0,079430 | 0,243449 | 1,683883 | 0,022425 | 0,044304 | 0,400428 | BP100,CG20,CG19b |
| MLP 37:39-40-19-1:1 | 0,063921 | 0,216093 | 1,799112 | 0,017571 | 0,039021 | 0,411345 | BP100,CG20,CG10b |
| MLP 34:36-20-1:1 | 0,274450 | 0,171115 | 1,467962 | 0,093004 | 0,038866 | 0,406097 | BP100,CG4c,CG17b |
| MLP 32:34-40-23-1:1 | 0,225192 | 0,181894 | 1,757623 | 0,065976 | 0,032451 | 0,394853 | BP100,CG20,CG2b |

Highlighted is the network with the lowest Mean Square Error (MSE) of the testing set.

Based on those findings, data was further analyzed using a system designed by the author  – JIM (Java Immunotherapy Model, further presented in “Chapter 6 JIM Computer analysis System”), in order to find the best possible network structure for the problem. The system employs genetic algorithms to choose the network’s architecture and trains it utilizing the backpropagation algorithm with added local minima skipping, based on the annealing algorithm [][#Joone:2005:Documentation]. 

JIM recorded a full specification of a network in a single chromosome. The chromosome comprised of the following variables:

* Inputs subset – a bit map of decision whether to use a given input
* First hidden layer width
* Second hidden layer width
* Third hidden layer width
* Type of layers used:
    * Hyperbolic tangent
    * Sigmoid
    * Logarithmic
* learningRate – the speed of learning
* momentum – initial speed of learning

Structure selection process consisted of evolving a population made of 60 members in 10 generations. A roulette method of selection was employed. The adaptation function arguments included:

* RMSE       the most important of the adaptation function value are reciprocal of RMSE
* Inputs number  
    Subsequent bits are reciprocal of the inputs number
* The sum of the hidden neurons  
    Last bits are reciprocal to the sums of the hidden layers neurons

### Interpretation of results – network quality
SNN application employs “error” and “quality” parameters to describe a given network. Those are calculated for the training, validation and testing sets. 
The “Error” is the Mean Square Error discussed in the introduction. “Quality” of a network working regression problems is defined as standard deviation of prediction errors divided by the standard deviation of the output values:

<< J=sigma_p/sigma_w >>

<< sigma_p >> - standard deviation of the prediction errors set  
<< sigma_w >> - standard deviation of the outputs set

For finite populations, the standard deviation is calculated using:

<< sigma = sqrt((sum_(i=1)^N(x_i-mu)^2)/(N(N-1)))>>

<< x_i >> - value of characteristic in population  
<< mu >> - expected value  
<< N >> - population number

The expected value for a population with constant probability is the mean population value. This means that the mean square difference of prediction error and mean prediction error is compared with the mean square difference between data and their mean value.  
Independent value of the standard deviation of the prediction errors set is not informative; therefore it needs to be compared with the standard deviation of the output values set. The starting point of comparing those two values could be a network model predicting constant output value equal to the mean output value of the data set. That model is wrong, since it is independent of the input values. Standard deviation of the prediction errors set will be equal to the standard deviation of the output set. The quotient will equal 1.  
For a desirable network correctly modeling the assignment, this value has to be significantly lower than 1. Prediction spread has to be lower than the output data spread. It is accepted that [][#Statistica:2004] the quality factor of 0.1 means a good regression. 

### Comparison of networks
The network with the lowest error of the testing set suggested by SNN is “MLP 12:12-20-14-1:1”. The network with the best quality is “MLP 13:13-20-12-1:1”. The comparison of those networks is presented in [Table 6][]. 

[Table 6 Comparison of the best networks][Table 6]
| Type | Quality u | Quality w | Quality t | Error u | Error w  | Error t  | training |
| --- |
| MLP 13:13-20-12-1:1  | 0,031235  | 0,335306  | 0,960763  | 0,010541  | 0,112789  | 0,398376  | BP100,CG3c,CG18b  |
| MLP 12:12-20-14-1:1  | 0,380690  | 0,498705  | 1,190093  | 0,121202  | 0,094067  | 0,279640  | BP100,CG20,CG0b  |

Networks were trained using the backpropagation algorithm and the conjugate gradient algorithm. First network has a quality factor value of 0.96 for the teting set, so its quality is nearing 1. For a network working regression problems the quality is defined as a quotient of standard deviations of prediction errors and standard deviations of data. A value of 1 of this factor means that the standard deviation of prediction errors is comparable to the standard deviation of data. This means that the network gives out results of quality comparable to those of a network modelling the mean output values. 

[Table 7 Comparison of expected values with the prediction of  best networks for the testing set][Table 7]
| No | Expected values | MLP 13:13-20-12-1:1 Prognosis | MLP 13:13-20-12-1:1 Error | MLP 12:12-20-14-1:1  Prognosis | MLP 12:12-20-14-1:1 Error |
| --- |
| 1 | 0,000000 | -0,043314 | 0,043314 | -0,118149 | 0,118149 |
| 2 | 0,000000 | -0,278319 | 0,278319 | -0,001623 | 0,001623 |
| 3 | 0,375000 | 0,186607 | 0,188393 | 0,262080 | 0,112920 |
| 4 | 0,250000 | 0,138617 | 0,111383 | 0,104553 | 0,145447 |
| 5 | 0,444444 | -0,080890 | 0,525334 | -0,154654 | 0,599098 |
| 6 | 0,000000 | 0,055671 | 0,055671 | -0,060779 | 0,060779 |
| 7 | 0,000000 | -0,295691 | 0,295691 | -0,074360 | 0,074360 |

Looking at the cases, high level of errors is apparent in values predicted for the testing set. **Predicted was the maximum deterioration of patient’s condition in one year, throughout the whole period of treatment.**
The research aim was to devise of a system supporting doctors when deciding on whether patients qualify for treatment. After consulting with an expert – dr Ewa Cichocka-Jarosz – on quality of the results, a hypothesis was formulated on predicting **whether deterioration will occur**. A classifying threshold was established at 7.5%. It means that, from the point of view of a doctor, a deterioration of 7.5% is not burdensome for the patients, measured in the amount of medication taken in order to function normally.  

[Table 8 Comparison of classifier expected values with predictions of best networks for the testing set][Table 8]
| No | Expected Values | Had deterioration not occured? | MLP 13: 13-20-12-1:1 | Had deterioration not occured? | Was prediction correct? | MLP 12: 12-20-14-1:1 | Had deterioration not occured? | Was prediction correct? |
| --- |
| 1 | 0,000000 | TRUE | -0,043310 | TRUE | TRUE | -0,118150 | FALSE | **FALSE** |
| 2 | 0,000000 | TRUE | -0,278320 | FALSE | **FALSE** | -0,001620 | TRUE | TRUE |
| 3 | 0,375000 | FALSE | 0,186607 | FALSE | TRUE | 0,262080 | FALSE | TRUE |
| 4 | 0,250000 | FALSE | 0,138617 | FALSE | TRUE | 0,104553 | FALSE | TRUE |
| 5 | 0,444444 | FALSE | -0,080890 | FALSE | TRUE | -0,154650 | FALSE | TRUE |
| 6 | 0,000000 | TRUE | 0,055671 | TRUE | TRUE | -0,060780 | TRUE | TRUE |
| 7 | 0,000000 | TRUE | -0,295690 | FALSE | **FALSE** | -0,074360 | TRUE | TRUE |

Wrong predictions were identified. The first network was mistaken on two patients: no.2 and no.7. The prediction success rate therefore was 71.5%. The second network failed with one patient – no.1. The success rate was 85.8%. 

[Table 9 Comparison of expected values with the predictions of the best networks for the validating set][Table 9]
| No | Expected Value | MLP 13:13-20-12-1:1 prognosis | MLP 13:13-20-12-1:1 error | MLP 12:12-20-14-1:1 prognosis | MLP 12:12-20-14-1:1 error |
| --- |
| 1 | 0,166667 | 0,251501 | 0,084834 | 0,314250 | 0,147583 |
| 2 | 0,363636 | 0,327791 | 0,035845 | 0,435394 | 0,071758 |
| 3 | 0,333333 | 0,354619 | 0,021286 | 0,297487 | 0,035846 |
| 4 | 0,000000 | 0,075336 | 0,075336 | 0,012003 | 0,012003 |
| 5 | 0,000000 | 0,073820 | 0,073820 | 0,121240 | 0,121240 |
| 6 | 0,000000 | 0,028417 | 0,028417 | 0,036233 | 0,036233 |
| 7 | 0,285714 | 0,422500 | 0,136786 | 0,306602 | 0,020888 |

[Table 10 Comparison of the classifier expected values with the best networks’ predictions for the validating set][Table 10]
| No | Expected values | Had deterioration not occured? | MLP 13: 13-20-12-1:1 | Had deterioration not occured? | Was prediction correct ? | MLP 12: 12-20-14-1:1 | Had deterioration not occured? | Was prediction correct ? |
| --- |
| 1 | 0,166667 | FALSE | 0,251501 | FALSE | TRUE | 0,31425 | FALSE | TRUE |
| 2 | 0,363636 | FALSE | 0,327791 | FALSE | TRUE | 0,435394 | FALSE | TRUE |
| 3 | 0,333333 | FALSE | 0,354619 | FALSE | TRUE | 0,297487 | FALSE | TRUE |
| 4 | 0,000000 | TRUE | 0,075336 | FALSE | **FALSE** | 0,012003 | TRUE | TRUE |
| 5 | 0,000000 | TRUE | 0,07382 | TRUE | TRUE | 0,12124 | FALSE | **FALSE** |
| 6 | 0,000000 | TRUE | 0,028417 | TRUE | TRUE | 0,036233 | TRUE | TRUE |
| 7 | 0,285714 | FALSE | 0,4225 | FALSE | TRUE | 0,306602 | FALSE | TRUE |

Failed prognosis were highlighted. The hypothesis on predicting by using a classifier gives the prognosis precision for the validating set at 86%.

[Table 11 Comaprison of expected values with the predictions of the best networks for thetraining set][Table 11]
| No | Expected Value | MLP 13:13-20-12-1:1 prognosis | MLP 13:13-20-12-1:1 error | MLP 12:12-20-14-1:1 prognosis | MLP 12:12-20-14-1:1 Error |
| --- |
| 1 | 0,000000 | -0,006501 | 0,006501 | 0,023957 | 0,023957 |
| 2 | 0,000000 | 0,007466 | 0,007466 | -0,005603 | 0,005603 |
| 3 | 0,000000 | 0,000447 | 0,000447 | 0,023219 | 0,023219 |
| 4 | 0,181818 | 0,178565 | 0,003253 | 0,231627 | 0,049809 |
| 5 | 0,000000 | -0,012607 | 0,012607 | -0,031640 | 0,031640 |
| 6 | 0,272727 | 0,267516 | 0,005211 | 0,274245 | 0,001518 |
| 7 | 0,600000 | 0,602406 | 0,002406 | 0,589727 | 0,010273 |
| 8 | 0,000000 | -0,012214 | 0,012214 | 0,004326 | 0,004326 |
| 9 | 0,000000 | 0,004209 | 0,004209 | -0,007642 | 0,007642 |
| 10 | 0,461538 | 0,455638 | 0,005900 | 0,429161 | 0,032377 |
| 11 | 0,125000 | 0,130785 | 0,005785 | 0,061191 | 0,063809 |
| 12 | 0,000000 | 0,006553 | 0,006553 | -0,014312 | 0,014312 |
| 13 | 0,333333 | 0,338616 | 0,005283 | 0,331784 | 0,001549 |
| 14 | 0,333333 | 0,331775 | 0,001558 | 0,283209 | 0,050124 |
| 15 | 0,000000 | 0,013126 | 0,013126 | -0,000022 | 0,000022 |
| 16 | 0,666667 | 0,669622 | 0,002955 | 0,634604 | 0,032063 |

[Table 12 Comparison of the classifier expected values with the best networks’ predictions for the training set][Table 12]
| No | Expected values | Had deterioration not occured? | MLP 13: 13-20-12-1:1 | Had deterioration not occured? | Was prediction correct | MLP 12: 12-20-14-1:1 | Had deterioration not occured? | Was prediction correct |
| --- |
| 1 | 0,000000 | TRUE | -0,006500 | TRUE | TRUE | 0,023957 | TRUE | TRUE |
| 2 | 0,000000 | TRUE | 0,007466 | TRUE | TRUE | -0,005603 | TRUE | TRUE |
| 3 | 0,000000 | TRUE | 0,000447 | TRUE | TRUE | 0,023219 | TRUE | TRUE |
| 4 | 0,181818 | FALSE | 0,178565 | FALSE | TRUE | 0,231627 | FALSE | TRUE |
| 5 | 0,000000 | TRUE | -0,012610 | TRUE | TRUE | -0,031640 | TRUE | TRUE |
| 6 | 0,272727 | FALSE | 0,267516 | FALSE | TRUE | 0,274245 | FALSE | TRUE |
| 7 | 0,600000 | FALSE | 0,602406 | FALSE | TRUE | 0,589727 | FALSE | TRUE |
| 8 | 0,000000 | TRUE | -0,012210 | TRUE | TRUE | 0,004326 | TRUE | TRUE |
| 9 | 0,000000 | TRUE | 0,004209 | TRUE | TRUE | -0,007642 | TRUE | TRUE |
| 10 | 0,461538 | FALSE | 0,455638 | FALSE | TRUE | 0,429161 | FALSE | TRUE |
| 11 | 0,125000 | FALSE | 0,130785 | FALSE | TRUE | 0,061191 | TRUE | **FALSE** |
| 12 | 0,000000 | TRUE | 0,006553 | TRUE | TRUE | -0,014312 | TRUE | TRUE |
| 13 | 0,333333 | FALSE | 0,338616 | FALSE | TRUE | 0,331784 | FALSE | TRUE |
| 14 | 0,333333 | FALSE | 0,331775 | FALSE | TRUE | 0,283209 | FALSE | TRUE |
| 15 | 0,000000 | TRUE | 0,013126 | TRUE | TRUE | -0,000022 | TRUE | TRUE |
| 16 | 0,666667 | FALSE | 0,669622 | FALSE | TRUE | 0,634604 | FALSE | TRUE |

One wrong prognosis is highlighted in the above table. All predictions of the first network were correct for the training set. For the second network 1 (of16) predictions had a slight error. The prognosis was that desensitizing of the no.11 patient will not result in deterioration. However, deterioration did occur, albeit slight. This means that the first network had a prediction success rate of 100%, and the second one of 93.7%.  
Analysis conducted in the JIM system confirmed the SNN results. JIM was markedly slower than SNN and had a strong tendency to learn a single output value, which was the mean arithmetic of the predictions. Interesting was the advantage that the hyperbolic tangent neural networks had over logarithmic and sigmoidal threshold functions network when modeling the problem.

### The interpretation of input parameters
From the perspective of the input parameters selection, “MLP 13:13-20-12-1:1” network is the most interesting one. Reducing the number of parameters to 13 in “MLP 12: 12-20-14-1:1” lowers the quality.

[Table 13][Table 13]
| Type | Quality u | Quality w | Quality t | Error u | Error w | Error t | Training |
| --- |
| MLP 13:13-20-12-1:1 | 0,031235 | 0,335306 | 0,960763 | 0,010541 | 0,112789 | 0,398376 | BP100,CG3c,CG18b |

Network parameters: 

Interview group:

* wiek_w_latach (age in years)
* rozpoznanie (diagnosis)
* natezenia_astmy_rozpoznanie (asthma severity diagnosed)
* w_czas_choroby_do_it_nos (lenght of sickness - nose)
* w_czas_choroby_do_it_astma (lenght of sickness - asthma)
* w_obciazajacy_wywiad_rodzinny (indicative family history interview)
* obciazenie_rodzinne (family history)

Group w1:

* eozynofilia (eosinophiles)
* eozynofilia_bezw (absolute eosinophiles)
* w_ecp 
* w_eozynofile_zdegranulowane (degranulated eosinophiles)
* w_fev
* w_procent_zmiennosci_po_beta (change percentage after beta)
