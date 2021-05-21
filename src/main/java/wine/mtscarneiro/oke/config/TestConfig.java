package wine.mtscarneiro.oke.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import wine.mtscarneiro.oke.entities.NearestStore;
import wine.mtscarneiro.oke.entities.Store;
import wine.mtscarneiro.oke.entities.Zip;
import wine.mtscarneiro.oke.repositories.NearestStoreRepository;
import wine.mtscarneiro.oke.repositories.StoreRepository;
import wine.mtscarneiro.oke.repositories.ZipRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ZipRepository zipRepository;

    @Autowired
    private NearestStoreRepository nearestStoreRepository;

    @Override
    public void run(String... args) throws Exception {

        Store s1 = new Store(null, "LOJA_PINHEIROS", new ArrayList<>());
        Store s2 = new Store(null, "LOJA_JARDINS", new ArrayList<>());
        Store s3 = new Store(null, "LOJA_MOEMA", new ArrayList<>());

        storeRepository.saveAll(Arrays.asList(s1, s2, s3));

        Zip z1 = new Zip(null, 100000, 200000, s1);
        Zip z2 = new Zip(null, 200001, 300000, s1);
        Zip z3 = new Zip(null, 300001, 400000, s2);
        Zip z4 = new Zip(null, 400001, 500000, s2);
        Zip z5 = new Zip(null, 500001, 600000, s3);
        Zip z6 = new Zip(null, 700001, 800000, s3);

        zipRepository.saveAll(Arrays.asList(z1, z2, z3, z4, z5, z6));

        s1.getDeliverRange().add(z1);
        s1.getDeliverRange().add(z2);
        s2.getDeliverRange().add(z3);
        s2.getDeliverRange().add(z4);
        s3.getDeliverRange().add(z5);
        s3.getDeliverRange().add(z6);

        NearestStore ns = new NearestStore(null, 105000, s1);

        NearestStore ns1 = new NearestStore(null, 100000,   s1);
        /*
        NearestStore ns2 = new NearestStore(null, 205000,   s1);
        NearestStore ns3 = new NearestStore(null, 405000,  s2);
        NearestStore ns4 = new NearestStore(null, 100100,  s1);
         */


        storeRepository.saveAll(Arrays.asList(s1, s2, s3));


        nearestStoreRepository.saveAll(Arrays.asList(ns, ns1));
        //Arrays.asList(s1, s2, s3)

    }
}
