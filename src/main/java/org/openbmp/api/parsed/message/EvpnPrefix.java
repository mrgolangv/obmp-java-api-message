package org.openbmp.api.parsed.message;
/*
 * Copyright (c) 2015-2018 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 */

import org.openbmp.api.parsed.processor.ParseLongEmptyAsZero;
import org.openbmp.api.parsed.processor.ParseNullAsEmpty;
import org.openbmp.api.parsed.processor.ParseTimestamp;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Format class for l3vpn_prefix parsed messages (openbmp.parsed.l3vpn_prefix)
 * <p>
 * Schema Version: 1.7
 */
public class EvpnPrefix extends Base {

    // Minimum set of headers each Object will have.
    public static String[] schemaHeaderNames = new String[] {
            MsgBusFields.ACTION.getName(),
            MsgBusFields.SEQUENCE.getName(),

            MsgBusFields.VPN_HASH.getName(),
            MsgBusFields.ROUTER_HASH.getName(),

            MsgBusFields.ROUTER_IP.getName(),
            MsgBusFields.PATH_HASH.getName(),

            MsgBusFields.PEER_HASH.getName(),
            MsgBusFields.PEER_IP.getName(),

            MsgBusFields.PEER_ASN.getName(),
            MsgBusFields.TIMESTAMP.getName(),

            MsgBusFields.ORIGIN.getName(),
            MsgBusFields.AS_PATH.getName(),

            MsgBusFields.AS_PATH_COUNT.getName(),
            MsgBusFields.ORIGIN_AS.getName(),

            MsgBusFields.NEXTHOP.getName(),
            MsgBusFields.MED.getName(),

            MsgBusFields.LOCAL_PREF.getName(),
            MsgBusFields.AGGREGATOR.getName(),

            MsgBusFields.COMMUNITY_LIST.getName(),
            MsgBusFields.EXT_COMMUNITY_LIST.getName(),

            MsgBusFields.CLUSTER_LIST.getName(),
            MsgBusFields.ISATOMICAGG.getName(),

            MsgBusFields.IS_NEXTHOP_IPV4.getName(),
            MsgBusFields.ORIGINATOR_ID.getName(),

            MsgBusFields.PATH_ID.getName(),
            MsgBusFields.ISPREPOLICY.getName(),

            MsgBusFields.IS_ADJ_RIB_IN.getName(),
            MsgBusFields.RdAdminSubfield.getName(),

            MsgBusFields.VPN_RD_TYPE.getName(),
            MsgBusFields.OriginRouterIpLen.getName(),

            MsgBusFields.OriginRouterIp.name(),
            MsgBusFields.EthernetTagIdHex.getName(),

            MsgBusFields.EthernetSegId.getName(),
            MsgBusFields.Mac_len.getName(),

            MsgBusFields.Mac.getName(),
            MsgBusFields.PREFIX_LEN.getName(),

            MsgBusFields.PREFIX.getName(),
            MsgBusFields.LABEL_1.getName(),

            MsgBusFields.LABEL_2.getName(),
            MsgBusFields.RouteType.getName(),

            MsgBusFields.GATEWAY.getName()

    };


    /**
     * base constructor to support backward compatibility.
     *
     * @param data
     */
    public EvpnPrefix(String data) {
        super();


        headerNames = schemaHeaderNames;

        parse(data);
    }


    /**
     * Handle the message by parsing it and storing the data in memory.
     *
     * @param version Float representation of maximum message bus specification version supported.
     *                See http://openbmp.org/#!docs/MESSAGE_BUS_API.md for more details.
     * @param data    TSV data (MUST not include the headers)
     */
    public EvpnPrefix(Float version, String data) {
        super();

        spec_version = version;

        headerNames = schemaHeaderNames;

        parse(version, data);
    }

    /**
     * Processors used for each field.
     * <p>
     * Order matters and must match the same order as defined in headerNames
     *
     * @return array of cell processors
     */
    protected CellProcessor[] getProcessors() {

        CellProcessor[] processors;

        final CellProcessor[] defaultCellProcessors = new CellProcessor[]{
                new NotNull(),// action
                new NotNull(),// evpn_seq
                new NotNull(),// vpn_hash_str.c_str()
                new NotNull(),// r_hash_str.c_str()
                new NotNull(),// router_ip.c_str()
                new ParseNullAsEmpty(),// path_hash_str.c_str()
                new ParseNullAsEmpty(),// p_hash_str.c_str()
                new NotNull(),// peer.peer_addr
                new ParseLong(),// peer.peer_as
                new ParseTimestamp(),// ts.c_str()
                new ParseNullAsEmpty(),             // origin
                new ParseNullAsEmpty(),             // as_path
                new ParseLongEmptyAsZero(),         // as_path_count
                new ParseLongEmptyAsZero(),         // origin_as
                new ParseNullAsEmpty(),             // nexthop
                new ParseLongEmptyAsZero(),         // med
                new ParseLongEmptyAsZero(),         // local_pref
                new ParseNullAsEmpty(),             // aggregator
                new ParseNullAsEmpty(),             // community_list
                new ParseNullAsEmpty(),             // ext_community_list
                new ParseNullAsEmpty(),             // cluster_list
                new ParseLongEmptyAsZero(),         // isAtomicAgg
                new ParseLongEmptyAsZero(),// attr->nexthop_isIPv4
                new ParseNullAsEmpty(),// attr->originator_id
                new ParseNullAsEmpty(),// vpn[i].path_id
                new ParseLongEmptyAsZero(),// peer.isPrePolicy
                new ParseLongEmptyAsZero(),// peer.isAdjIn
                new ParseNullAsEmpty(),// vpn[i].rd_administrator_subfield.c_str():vpn[i].rd_assigned_number.c_str()
                new ParseNullAsEmpty(),// vpn[i].rd_type
                new ParseNullAsEmpty(),// vpn[i].originating_router_ip_len
                new ParseNullAsEmpty(),// vpn[i].originating_router_ip
                new ParseNullAsEmpty(),// vpn[i].ethernet_tag_id_hex
                new ParseNullAsEmpty(),// vpn[i].ethernet_segment_identifier
                new ParseNullAsEmpty(),// vpn[i].mac_len
                new ParseNullAsEmpty(),// vpn[i].mac
                new ParseNullAsEmpty(),// vpn[i].ip_len
                new ParseNullAsEmpty(),// vpn[i].ip
                new ParseNullAsEmpty(),// vpn[i].mpls_label_1
                new ParseNullAsEmpty(),// vpn[i].mpls_label_2
                new ParseInt(),// vpn[i].route_type
                new ParseNullAsEmpty(),// vpn[i].gateway
        };

        if (spec_version.compareTo((float) 1.7) >= 0) {
            CellProcessor[] versionSpecificProcessors = new CellProcessor[]{
                    new ParseLongEmptyAsZero(),         // Path ID
                    new ParseNullAsEmpty(),             // Labels
                    new ParseLongEmptyAsZero(),         // isPrePolicy
                    new ParseLongEmptyAsZero(),         // isAdjRibIn
                    new ParseNullAsEmpty()              // Large Communities
            };

            List<CellProcessor> processorsList = new ArrayList();
            processorsList.addAll(Arrays.asList(defaultCellProcessors));
            processorsList.addAll(Arrays.asList(versionSpecificProcessors));

            processors = processorsList.toArray(new CellProcessor[processorsList.size()]);

        } else if (spec_version.compareTo((float) 1.3) >= 0) {

            CellProcessor[] versionSpecificProcessors = new CellProcessor[]{
                    new ParseLongEmptyAsZero(),         // Path ID
                    new ParseNullAsEmpty(),             // Labels
                    new ParseLongEmptyAsZero(),         // isPrePolicy
                    new ParseLongEmptyAsZero()          // isAdjRibIn
            };

            List<CellProcessor> processorsList = new ArrayList();
            processorsList.addAll(Arrays.asList(defaultCellProcessors));
            processorsList.addAll(Arrays.asList(versionSpecificProcessors));

            processors = processorsList.toArray(new CellProcessor[processorsList.size()]);
        } else if (spec_version.compareTo((float) 1.1) >= 0) {

            CellProcessor[] versionSpecificProcessors = new CellProcessor[]{
                    new ParseLongEmptyAsZero(),         // Path ID
                    new ParseNullAsEmpty()              // Labels
            };

            List<CellProcessor> processorsList = new ArrayList();
            processorsList.addAll(Arrays.asList(defaultCellProcessors));
            processorsList.addAll(Arrays.asList(versionSpecificProcessors));

            processors = processorsList.toArray(new CellProcessor[processorsList.size()]);

        } else {
            processors = defaultCellProcessors;
        }

        return processors;
    }
}
