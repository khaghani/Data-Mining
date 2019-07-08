/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Data;

import Elasticsearch.client.Elastic;
import DataMining.Text.TextAnalysis;
import Elasticsearch.client.ElasticNode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.App.Global;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;

/**
 *
 * @author amir
 */
public class ElasticSearch {

    private String IP = Global.ElasticConfiguration.IP;
    private int PORT = Global.ElasticConfiguration.PORT;

    private String _sentence;
    private Elastic _elastic;
    private ElasticNode[] nodes;
    private TextAnalysis _tAnalysis;

    private int hit = 0;
    private double total, present;

    private boolean isReady = false;
///////////////////////////////////////////////////////////////////

    public enum By {
        Word,
        Sentence
    }

    ///////////////////////////////////////////////////////////////////
    public ElasticSearch(String sentence) {
        isReady = setSentence(sentence);
    }

    public ElasticSearch(String ip, int port) {
        this(null, ip, port);
    }

    public ElasticSearch(String sentence, String ip, int port) {
        this.PORT = port;
        this.IP = ip;
        isReady = setSentence(sentence) && Init();
    }

    ///////////////////////////////////////////////////////////////////
    public String getSentence() {
        return this._sentence;
    }

    public boolean setSentence(String value) {
        if (value == null) {
            return false;
        }
        this._sentence = value;
        return isReady = true;
    }

    ///////////////////////////////////////////////////////////////////
    private boolean Init() {
        _tAnalysis = new TextAnalysis(_sentence, false);
        _elastic = new Elastic(IP, PORT);
        hit = 0;

        return true;
    }

    public double getPresent() {
        if (total == 0.0) {
            return 0;
        }
        return present = (hit / total) * 100;
    }

    public boolean Proccesing() {
        preProccesing();
        return true;
    }

    private boolean preProccesing() {
        boolean result = _tAnalysis.StartAnalysis();
        ElasticNode node;
        if (result) {
            total = _tAnalysis.getPeWordsCount();
            List<String> words = _tAnalysis.getPeWords();
//            nodes = new ElasticNode[(int) total];
            for (int index = 0; index < total; index++) {
                String word = words.get(index);
                if (word != null) {
                    node = new ElasticNode(word);
                    //nodes[index].setID(word);
                    String body = "{\"query\":{\"term\":{\"body.word\":\""
                            + word + "\"}}}";
                    node.setIndexName("jmachinelearning_persianword/_search");
                    JSONObject ss = null;
                    try {
                        ss = new JSONObject(_elastic.customQuery(node.getIndexName(),
                                body, Connection.Method.POST));
                        if (ss.getJSONObject("hits").getInt("total") > 0) {
                            hit++;
                        }
                    } catch (JSONException ex) {
                        result = false;
                        Logger.getLogger(ElasticSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        return result;
    }
    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
}
