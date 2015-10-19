package com.olacabs.customer.app;

import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.olacabs.customer.app.o */
public final class PublicKeyManager implements X509TrustManager {
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate array is null");
        } else if (x509CertificateArr.length <= 0) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
        } else if (str == null || !str.contains("RSA")) {
            throw new CertificateException("checkServerTrusted: AuthType does not contains RSA");
        } else {
            try {
                int i;
                TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                instance.init((KeyStore) null);
                for (TrustManager trustManager : instance.getTrustManagers()) {
                    ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
                }
                String bigInteger = new BigInteger(1, ((RSAPublicKey) x509CertificateArr[0].getPublicKey()).getEncoded()).toString(16);
                if ("30820122300d06092a864886f70d01010105000382010f003082010a0282010100b4a5e3bffd9c0b0f4ba799a03b234db1f93381f8725c73ab1917ddea91dddc2ea9a569e4817f7903adc549fa464c73ff9df21bbe371c2adbc08a35a245c4f188d76ac5113e909f42af3557f467240469f0276c907c7387bb1a43797be6cf0f456b3bc072a4cbc21700525a3de9d2918fd6c00942c12d13c3de7ecea505af9bbdff879ca209d12013de216a20fda7d80016ba2d024ceebfc526a64196862303e3fb53f2318cca44e09815722a6e94c0ef4725858f7e17e773785f5fe29d83dbf1aa59308523758c14211cbe3d4f1027d330a711da840931e11166d141f1318e66974bbbbf74e34ec3f1e8c1b1a220c1f11e41c123c5f9c6580eba09bd05de397f0203010001".equalsIgnoreCase(bigInteger) || "30820122300d06092a864886f70d01010105000382010f003082010a0282010100a534d3648e57ff5d456fe49395fed1114639b496dbd2966592a855fe9de401f93136bf4f309c23740a324355b911951ac1ad540c67e4fa4140494c6aa4d5cb2a61d5d5d0bb9effdb07857e01a601c3e65e94f8994a6340f13d41cd4e0cc818de056c9bd0eb495dece7954f3ff26318f391ce74539c894b04236615e269ba837fd523d6ae4d500f0630425954195f835f8349b97fb2fc18613f08dda279a5dddcaaf03c439b573ef1c0ef84ca66e97e1f471c5b23d3090c17f613301d896318bf344595a46ba8acc5a76824429b276172d22086c0b4407477e2f8dd33488ee19a3776994ffb6d96ff22b8418977bf7954c8c1d0e5d160aefa7654bc519b2e80350203010001".equalsIgnoreCase(bigInteger) || "30820122300d06092a864886f70d01010105000382010f003082010a0282010100a2e4e3a91394f583bd00126969473a1c53e4beaf168f7b245769289fc15cb4a47f3e248cb4903e3c13a3fa54a85b6ae03042c79b7772fd82b10099868a86bd21466ca886d88b373a5da6a50061fe566c862cb15d10b871d5a364005b0e84aae6f70a284d0f883311cf657292874e2bbee62909852905324e4ca436c9135d433bd9c2d404f2eb94b901563866f1fa97bd5066af18847522e305670ad6a66113baf20f5037f663463d8a79c6965565db61e1618fa58f82f273c071b5116dda5a27f7751a32c2f6a7eb88bc0ad3f6e80e22d89a1dca2fdf523a90aa9ef4686217cb032907f175e1e09059f23ae8b3b3b3f919ceee618508e2a76e44fc1a665c0c3f0203010001".equalsIgnoreCase(bigInteger) || "30820122300d06092a864886f70d01010105000382010f003082010a0282010100dc9d25e92663e85ebf7eeab1b8f85547e7e138211d3388095e497ae7bfbea104cb7f3fa3ce5d2beeec5d3a7332b5f1c96a668b7835a2d7a2507587ac8da94b452b21a420c9abac3d2057b6505376ad486f3423750a97ba9f20cd7a6bd811f5fd9ebdefddd969f115651e2e68c8660d78cae014d063f037eacc00bf412b71a5862700fb6a8bc3cdbd8edbeebc484a1a87b91d2b86ae6254c9e3e4a012148d74a550ce549985fdf5583f18e66538295f3f5ebffa45c317eb70177ddb5ba79b7228445df169b065f1ca6ae59c7c8f7e528ba0db758b0e886eed26e47f6ae3403a8125f125d141468157327ae66867101d3492ebe66f33296152ae37a0fae84696110203010001".equalsIgnoreCase(bigInteger) || "30820122300d06092a864886f70d01010105000382010f003082010a0282010100dc9d25e92663e85ebf7eeab1b8f85547e7e138211d3388095e497ae7bfbea104cb7f3fa3ce5d2beeec5d3a7332b5f1c96a668b7835a2d7a2507587ac8da94b452b21a420c9abac3d2057b6505376ad486f3423750a97ba9f20cd7a6bd811f5fd9ebdefddd969f115651e2e68c8660d78cae014d063f037eacc00bf412b71a5862700fb6a8bc3cdbd8edbeebc484a1a87b91d2b86ae6254c9e3e4a012148d74a550ce549985fdf5583f18e66538295f3f5ebffa45c317eb70177ddb5ba79b7228445df169b065f1ca6ae59c7c8f7e528ba0db758b0e886eed26e47f6ae3403a8125f125d141468157327ae66867101d3492ebe66f33296152ae37a0fae84696110203010001".equalsIgnoreCase(bigInteger)) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i == 0) {
                    throw new CertificateException("checkServerTrusted: public key mismatched");
                }
            } catch (Throwable e) {
                throw new CertificateException(e);
            }
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
