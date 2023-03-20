package com.test;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class ReadJSONFile {
    public static void main(String[] args) throws Exception {
        String fileName = "C:\\softs\\points.json";
        FileReader in = new FileReader(fileName);
        JsonReader reader = new JsonReader(in);
        reader.beginObject();
        String rootName = null;
        while (reader.hasNext()) {
            rootName = reader.nextName();
            if("data".equals(rootName)){
                System.out.println("-->"+rootName);
                reader.beginObject();
                while (reader.hasNext()) {
                    String cicel1 = reader.nextName();
                    if ("items".equals(cicel1)){
                        reader.beginArray();
                        while (reader.hasNext()) {
                            reader.beginObject();
                            while (reader.hasNext()){
                                System.out.println(reader.nextName() + ":" + reader.nextString());
                            }
                            reader.endObject();
                            /*if ("pointName".equals(reader.nextName())){
                                System.out.println(reader.nextName() + ":" + reader.nextString());
                            }*/

                        }
                        reader.endArray();
                    }
                }
                reader.endObject();
            }else {
            }
            /*if("INFO".equals(rootName)) {
                reader.beginObject();
                while (reader.hasNext()) {
                    System.out.println(reader.nextName() + ":" + reader.nextString())
                }
                reader.endObject();
            }else if("ATTR".equals(rootName)) {
                reader.beginObject();
                while (reader.hasNext()) {
                    System.out.println(reader.nextName() + ":" + reader.nextString())
                }
                reader.endObject();
            }else if("Parms".equals(rootName)) {
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    String k = null;
                    while (reader.hasNext()) {
                        k = reader.nextName();
                        switch (k) {
                            case "k":
                                xxx;
                                break;
                            case "v":
                                xxx;
                                break;
                            case "p":
                                xxx;
                                break;
                            case "m":
                                xxx;
                                break;
                            case "l":
                                xxx;
                                break;
                            default:
                                reader.nextString();
                                break;
                        }
                    }
                    reader.endObject();
                }
                reader.endArray();
            }else if("List".equals(rootName)) {
                reader.beginArray();
                while (reader.hasNext()) {
                    System.out.println(reader.nextString());
                }
                reader.endArray();
            }else {
                reader.skipValue();
            }*/
        }

    }

}


