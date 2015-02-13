/*
 * AISMessages
 * - a java-based library for decoding of AIS messages from digital VHF radio traffic related
 * to maritime navigation and safety in compliance with ITU 1371.
 * 
 * (C) Copyright 2011-2013 by S-Consult ApS, DK31327490, http://s-consult.dk, Denmark.
 * 
 * Released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * For details of this license see the nearby LICENCE-full file, visit http://creativecommons.org/licenses/by-nc-sa/3.0/
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 * 
 * NOT FOR COMMERCIAL USE!
 * Contact sales@s-consult.dk to obtain a commercially licensed version of this software.
 * 
 */

package dk.tbsalling.aismessages.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.dk.tbsalling.util.function.Consumer;
import dk.tbsalling.aismessages.dk.tbsalling.util.function.Supplier;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.BIT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

/**
 * broadcast message with unspecified binary payload. The St. Lawrence Seaway
 * AIS system and the USG PAWSS system use this payload for local extension
 * messages. It is variable in length up to a maximum of 1008 bits (up to 5
 * AIVDM sentence payloads).
 * 
 * @author tbsalling
 * 
 */
@SuppressWarnings("serial")
public class BinaryBroadcastMessage extends AISMessage {

    public BinaryBroadcastMessage(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected BinaryBroadcastMessage(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.BinaryBroadcastMessage;
    }

    @SuppressWarnings("unused")
	public Integer getDesignatedAreaCode() {
        return getDecodedValue(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return designatedAreaCode;
            }
        }, new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                designatedAreaCode = value;
            }
        }, new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return Boolean.TRUE;
            }
        }, new Supplier<Integer>() {
            @Override
            public Integer get() {
                return UNSIGNED_INTEGER_DECODER.apply(BinaryBroadcastMessage.this.getBits(38, 52));
            }
        });
	}

    @SuppressWarnings("unused")
	public Integer getFunctionalId() {
        return getDecodedValue(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return functionalId;
            }
        }, new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                functionalId = value;
            }
        }, new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return Boolean.TRUE;
            }
        }, new Supplier<Integer>() {
            @Override
            public Integer get() {
                return UNSIGNED_INTEGER_DECODER.apply(BinaryBroadcastMessage.this.getBits(52, 56));
            }
        });
	}

    @SuppressWarnings("unused")
	public String getBinaryData() {
        return getDecodedValue(new Supplier<String>() {
            @Override
            public String get() {
                return binaryData;
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String value) {
                binaryData = value;
            }
        }, new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return Boolean.TRUE;
            }
        }, new Supplier<String>() {
            @Override
            public String get() {
                return BIT_DECODER.apply(BinaryBroadcastMessage.this.getBits(52, 56));
            }
        });
	}

    @Override
    public String toString() {
        return "BinaryBroadcastMessage{" +
                "messageType=" + getMessageType() +
                ", designatedAreaCode=" + getDesignatedAreaCode() +
                ", functionalId=" + getFunctionalId() +
                ", binaryData='" + getBinaryData() + '\'' +
                "} " + super.toString();
    }

    private transient Integer designatedAreaCode;
	private transient Integer functionalId;
	private transient String binaryData;
}
