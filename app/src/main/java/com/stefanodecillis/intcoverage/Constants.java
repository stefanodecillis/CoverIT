package com.stefanodecillis.intcoverage;

/**
 * Created by stefanodecillis on 26/10/2017.
 */

public class Constants {
    //fetching TAG
    public static final String fetching = "FetchState";
    public static final String err_fetch = "FetchErr";

    //toast
    public static final String searching = "Searching..";
    public static final String err_fields = "Empty fields";

    //intent String
    public static final String intent_extra = "shapeLineObj";

    //states of APIs
    public static final String active = "Attivo";
    public static final String inactive = "NO";
    public static final String URLMAP = "http://marlin.planetel.it/netmap/";


    //connections
    public static final String NGA_VULA= "COPERTURA BITSTREAM NGA VULA FTTCab";
    public static final String VDSL_NGA_VULA= "COPERTURA EVDSL BITSTREAM NGA VULA FTTCab";
    public static final String FIBR_FTTH= "COPERTURA FTTH";
    public static final String ASIM_ATM= "COPERTURA BITSTREAM ASIM ATM";
    public static final String ASIM_ETH= "COPERTURA BITSTREAM ASIM ETHERNET";
    public static final String SIMM_ATM= "COPERTURA BITSTREAM SIMM ATM (2M,4M,6M,8M)";
    public static final String SIMM_ETH= "COPERTURA BITSTREAM SIMM ETHERNET";
    public static final String ULL = "COPERTURA ULL";
    public static final String SLU = "COPERTURA SLU";
    public static final String TERM_ETH_FIBRA = "COPERTURA CIRCUITI TERM ETHERNET FIBRA OTTICA";
    public static final String WLR_STATUS = "COPERTURA WLR";

    //descriptions
    public static final String NGA_VULA_DESCR = "VULA: TIM determina la velocità del link DSL, ma consegna subito il traffico al tuo operatore.\n" +
            "NGA: TIM determina la velocità del link DSL e trasporta i dati sulla propria rete fino al punto di interconnessione con il tuo operatore.";
    public static final String SLU_DESCR = "Nelle reti di telecomunicazione multi-operatore, con Sub Loop Unbundling (SLU) si indica una rete proprietaria che " +
            "arriva fino all'armadio ripartilinea (cabinet) dove, dopo un'eventuale conversione ottico-elettrica, viene allacciata all'ultimo miglio gestito da altro operatore";
    public static final String FTTC_DESCR = "FTTCab (Fiber-to-the-cabinet): il collegamento arriva in una cabina esterna molto vicina alla sede dell'utente o " +
            "al successivo armadio distributore, tipicamente entro 300 metri.";
    public static final String FTTH_DESCR = "FTTH (Fiber-to-the-home): il collegamento in fibra ottica raggiunge la singola unità abitativa, per " +
            "esempio una scatola sul muro di una casa. È la soluzione più costosa, ma anche l'investimento a più lungo termine che garantisce la massima velocità di trasmissione " +
            "fino all'utente finale in previsione di servizi di rete più evoluti.";
    public static final String ULL_DESCR = "Con Unbundling Local Loop, sigla ULL, si indica la possibilità che hanno i nuovi operatori telefonici di usufruire delle infrastrutture di proprietà di un altro operatore " +
            "(nel caso di Telecom Italia l'ultimo miglio in rame) per offrire ai clienti servizi propri dietro pagamento di un canone all'operatore proprietario delle infrastrutture.";
    public static final String BITSTREAM_DESCR ="È un servizio di interconnessione all'ingrosso (o 'wholesale') che consiste nella fornitura, da parte dell'operatore di telecomunicazioni dominante nel mercato delle reti di accesso (Telecom Italia in Italia)," +
            " della capacità trasmissiva tra la postazione di un cliente finale ed un punto di interconnessione o POP (Point of presence) di un altro operatore OLO che, a sua volta, vuole offrire servizi a " +
            "banda larga ai propri clienti finali.";

    //saved view
    public static final String JSON_RESULT = "json_result_savedInstance";
}
