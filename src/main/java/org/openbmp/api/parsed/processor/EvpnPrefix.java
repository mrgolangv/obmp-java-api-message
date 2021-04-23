/*
 * Copyright (c) 2018 Cisco Systems, Inc. and others.  All rights reserved.
 * Copyright (c) 2018 Tim Evens (tim@evensweb.com).  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 */
package org.openbmp.api.parsed.processor;


import org.openbmp.api.helpers.split;
import org.openbmp.api.parsed.message.EvpnPrefixPojo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * TSV Processor class
 *      Produces a list of UnicastPrefixPojo for each record in the message.
 */
public class EvpnPrefix {
    public List<EvpnPrefixPojo> records;            // Parsed records

    /**
     * Constructor
     *
     * @param data          Ascii/String TSV records delimited by newline
     */
    public EvpnPrefix(String data) {
        records = parseData(data);
    }

    List<EvpnPrefixPojo> parseData(String data) {
        List<String> records = split.getStrings(data, '\n', 5000);
        List<EvpnPrefixPojo> results = new ArrayList<>();

        for (String record: records) {
            if (record.length() > 3) {
                EvpnPrefixPojo entry = parseRecord(record);
                results.add(entry);
            }
        }

        return results;
    }

    EvpnPrefixPojo parseRecord(String data) {

        List<String> fields = split.getStrings(data, '\t', 500);

        EvpnPrefixPojo evpn = new EvpnPrefixPojo();

        /*
         * Populate pojo
         * debug
         */
        try {
            try {
                evpn.setWithdrawn(!fields.get(0).equals("add"));
            } catch (Exception ex) {
                System.out.println("setWithdrawn");
            };
            try {
                evpn.setSequence(BigInteger.valueOf(Long.parseLong(fields.get(1))));
            } catch (Exception ex) {
                System.out.println("setSequence");
            };
            try {
                evpn.setHash(fields.get(2));
            } catch (Exception ex) {
                System.out.println("setHash");
            };
            try {
                evpn.setVpnHash(fields.get(3));
            } catch (Exception ex) {
                System.out.println("setVpnHash");
            };
            try {
                evpn.setRouter_ip(fields.get(4));
            } catch (Exception ex) {
                System.out.println("setRouter_ip");
            };
            try {
                evpn.setPath_attr_hash_id(fields.get(5));
            } catch (Exception ex) {
                System.out.println("setPath_attr_hash_id");
            };
            try {
                evpn.setPeer_hash(fields.get(6));
            } catch (Exception ex) {
                System.out.println("setPeer_hash");
            };
            try {
                evpn.setPeer_ip(fields.get(7));
            } catch (Exception ex) {
                System.out.println("setPeer_ip");
            };
            try {
                evpn.setPeer_asn(Long.valueOf(fields.get(8)));
            } catch (Exception ex) {
                System.out.println("setPeer_asn");
            };
            try {
                evpn.setTimestamp(fields.get(9));
            } catch (Exception ex) {
                System.out.println("setTimestamp");
            };
            try {
                evpn.setOrigin(fields.get(10));
            } catch (Exception ex) {
                System.out.println("setOrigin");
            };
            try {
                evpn.setAs_path(fields.get(11));
            } catch (Exception ex) {
                System.out.println("setAs_path");
            };
            try {
                evpn.setAs_path_len(fields.get(12).length() != 0 ? Integer.parseInt(fields.get(12)) : 0);
            } catch (Exception ex) {
                System.out.println("setAs_path_len");
            };
            try {
                evpn.setOrigin_asn(fields.get(13).length() != 0 ? Long.parseLong(fields.get(13)) : 0L);
            } catch (Exception ex) {
                System.out.println("setOrigin_asn");
            };
            try {
                evpn.setNext_hop(fields.get(14));
            } catch (Exception ex) {
                System.out.println("setNext_hop");
            };
            try {
                evpn.setMed(fields.get(15).length() != 0 ? Long.parseLong(fields.get(15)) : 0L);
            } catch (Exception ex) {
                System.out.println("setMed");
            };
            try {
                evpn.setLocal_pref(fields.get(16).length() != 0 ? Long.parseLong(fields.get(16)) : 0L);
            } catch (Exception ex) {
                System.out.println("setLocal_pref");
            };
            try {
                evpn.setAggregator(fields.get(17));
            } catch (Exception ex) {
                System.out.println("setAggregator");
            };

            try {
                evpn.setCommunity_list(fields.get(18));
            } catch (Exception ex) {
                System.out.println("setCommunity_list");
            };

            try {
                evpn.setExt_community_list(fields.get(19));
            } catch (Exception ex) {
                System.out.println("setExt_community_list");
            };

            try {
                evpn.setCluster_list(fields.get(20));
            } catch (Exception ex) {
                System.out.println("setCluster_list");
            };

            try {
                evpn.setAtomicAggregate(fields.get(21).length() != 0 || fields.get(21).equals(0) ? false : true);
            } catch (Exception ex) {
                System.out.println("setAtomicAggregate");
            };

            try {
                evpn.setNextHopIpv4(fields.get(22).length() == 0 || fields.get(22).equals(0) ? false : true);
            } catch (Exception ex) {
                System.out.println("setNextHopIpv4");
            };

            try {
                evpn.setOriginator_id(fields.get(23));
            } catch (Exception ex) {
                System.out.println("setOriginator_id");
            };

            try {
                evpn.setPath_id(Long.valueOf(fields.get(24)));
            } catch (Exception ex) {
                System.out.println("setPath_id");
            };
            try {
                evpn.setPrePolicy(fields.get(25).equals(1) ? true : false);
            } catch (Exception ex) {
                System.out.println("setPrePolicy");
            };
            try {
                evpn.setAdjRibIn(fields.get(26).equals(1) ? true : false);
            } catch (Exception ex) {
                System.out.println("setAdjRibIn");
            };
            try {
                evpn.setRdAdminSubfield(fields.get(27));
            } catch (Exception ex) {
                System.out.println("setRdAdminSubfield");
            };
            try {
                evpn.setRdType(Integer.valueOf(fields.get(28)));
            } catch (Exception ex) {
                System.out.println("setRdType");
            };
            try {
                evpn.setOriginRouterIpLen(Integer.valueOf(fields.get(29)));
            } catch (Exception ex) {
                System.out.println("setOriginRouterIpLen");
            };
            try {
                evpn.setOriginRouterIp(fields.get(30));
            } catch (Exception ex) {
                System.out.println("setOriginRouterIp");
            };
            try {
                evpn.setEthernetTagIdHex(fields.get(31));
            } catch (Exception ex) {
                System.out.println("setEthernetTagIdHex");
            };
            try {
                evpn.setEthernetSegId(fields.get(32));
            } catch (Exception ex) {
                System.out.println("setEthernetSegId");
            };
            try {
                evpn.setMac_len(Integer.valueOf(fields.get(33)));
            } catch (Exception ex) {
                System.out.println("setMac_len");
            };
            try {
                evpn.setMac(fields.get(34));
            } catch (Exception ex) {
                System.out.println("setMac");
            };
            try {
                evpn.setPrefix_len(Integer.valueOf(fields.get(35)));
            } catch (Exception ex) {
                System.out.println("setPrefix_len");
            };
            try {
                evpn.setPrefix(fields.get(36));
            } catch (Exception ex) {
                System.out.println("setPrefix");
            };
            try {
                evpn.setLabel_1(Long.valueOf(fields.get(37)));
            } catch (Exception ex) {
                System.out.println("setLabel_1");
            };
            try {
                evpn.setLabel_2(Long.valueOf(fields.get(38)));
            } catch (Exception ex) {
                System.out.println("setLabel_2");
            };
            try {
                evpn.setRouteType(Integer.valueOf(fields.get(40)));
            } catch (Exception ex) {
                System.out.println("setRouteType");
            };
            try {
                evpn.setGateway(fields.get(39));
            } catch (Exception ex) {
                System.out.println("setGateway");
            };


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return evpn;
    }
}
