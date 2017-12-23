package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 <hr>
 <h2>Weird Strategy :</h2>
 <h3>This is a doomed and abandonnded strategy...<br>
 But we have hope that, one day, it shall rise and shine</h3>
 Note : for now it has a score of 39 :/
 <hr>
 */
public class WeirdStrategy extends Strategy {

    private ArrayList<Video> listVideosInCache = new ArrayList<>();
    private HashMap<Cache, ArrayList<Video>> videosToCache = new HashMap();

    public WeirdStrategy(DataBundle data) {
        super(data, 8);
    }

    @Override
    public void apply() {

        // Put in the hash map an association of cashes and videos
        for (Video video : data.getVideos()) {
            connectVideoToBestCache(video, data.getEndPoints(), data.getConnections(), data.getCaches());
        }

        for (int i = 0; i < data.getCaches().size() - 1; i++) {
            for (int j = 0; j < listVideosInCache.size() - 1; j++) {
                data.getCaches().get(i).addVideo(videosToCache.get(data.getCaches().get(i)).get(j));
            }
        }
    }

    /*
     Hey ! Lookout !
     */
    private void connectVideoToBestCache(Video video, ArrayList<EndPoint> endpoints, ArrayList<Connection> connections, ArrayList<Cache> caches) {

    //region
        /*
              __...-=--=-=--=---=---=-..__
 .,--~                            ~~--.._
'                .,;;!!!!!!!;;,,,.       `--._
             ;;!!'`.,,,,.````````'''>>;;,     `-._
          ;!!!'' ,`..`"$$$c,`"$$$$$cccc,.` .,,,.  `-.
         <!!` c"' z$P"  `""??h= "$$$$$$P =???$$$,    `-
       ;!!! d$$ccJ".d$$$$$hc ?$$$,,.  .,,.""=c $       `-.
      <!!!! $$$$F ,,      ""h $$$$$F-?????$$c,            `.
    ,c.`!!',$$$$ ?$$        $ 3$$$F        ,."              `
  .c$$$ :! <$$$$L "$L     ,c$ d$$$F        J$$'              `.
  $$$$$ `!>`$$$$$$c,`"????"",cP?$$L  ==ccc$$P'                 `.
  $$$$$c !'<$$$$$$$$$hccccd$$$L;$$$h  c,,.,cc                    `.
  ?$$$$',! J$$$$$$$$$$$$$$$$$$$$P"",c,`$$$$$$h                     :
   "$$' <'4$$$$$$$$$$$$$$$$$$$$$F,$$$$,$$$$$$$L.                    :
     ;;!'.$$$$$$$$$$$$$$$$$$$$$$hcc:hh$$$$$$$$$$                     \
    '!!! $$$$$$$$$$$$$$F"????????"""""?$$$$$$$$$L                     \
     !! 4$$$$$$$$$$$$L,c  .,,,,,,,,,,. ?$$$$$$$$$                      `.
     !>.$$$$$$$$$$$$$$$$L   `````````  J$$$$$$$$$. Hmm, yes the for here is made out of for
     !>`$$$$$$$$$$$$$$$$$$ `  ```````  J$$$$$$$$$'                       ;
    '!! $$$$$$$$$$$$$$$$$$$$$$$$$ccccc??$$$$$$$$$                     . z$
    <!! $$$$$$$$$$$$$$$$$$$$$$$cccccccc$$$$$$$$$$                   d$F.$$
    !!! 3$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$       ;;;;!!!!!! ,$P"4$$
    !!! `$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ,;!!!!!!!!!'' ,xnnnn "'.
    !!!! $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$P"?$$$$$$$ !!!!!!!'',xdMMMMMMP ;!!!
   ;!!!!>`$$$$$$$$$$$$$$$$$$$$$$$$$$$$ chc`3$$$$F,!'''',nnMMMMMMMP" ;!!!!!
   <!!!!! ?$$$$$$$$$$$$$$$$$$$$$$$$$$$hcccJ$$$P" ,nMMMMMMMMMMMMP ;!!!!!!!!
   !!!!!!> "$$$$$$$$$$$$$$$$$$$$$$$$$$$$P""",xnMMMMMMMPPP"""'.  <!!!!!!!!!
  ;!!!!!!!!; "?$$$$$$$$$$$$$$$$$P""'.,nmdMMM??"""..;;;;<!!!!!' <!!!!!!!!!
  !!!!!!!!!!!;,.`""???$$$??""',,nn????"""...;;!'!!!!!!!!!!!!` <!!!!!!!!! ,
  !!!!!!!!!!!!!!!!''`...-='"...;;>-''``.    ;;  !!!!!!!!!! .<!!!!!!!!!! ,M
 '!!!!!!!!''``  ,-='"..;;;!''``..;;.    -'   ' !!!!!!!!!! ;!!!!!!!!!!' dMP
 ''``..;;;;;!!'`  -```  .> `' ` ` ``' `..; ;!> !!!!!!!!' ;!!!!!!!!!!',MMM
!!!!!!!!!!!!!! ; ''''' ''  -   <; --' ''''```..!!!!!!!' !!!!!!!!!!' uMM" J
'''.,xmnmmr `!> > >'..>  '  `.,;;;!!!!!!!!!!!!!!!!!!' ;!!!!!!!!!!',dMP uMM
MMMMMPP"""  ;!! `  .,,;;!!!!!!!!!!''''''''''''!!!!! ;!!!!!!!!!!! uMMP MMMM
MMM",ndMMP  <!!!!!!!!!!!!!''``.,nmnMMMMMMMMMM !!!! <!!!!!!!!!!! uMMP dMMMM
" ,dMMMMMB  !!!!!!!'' .   .,,,,. "44MMMMMMMM' !!!!!!!!!!!!!!!! uMMP dMMMMM
 4MMMP").r' !!!!'.,nP ,dMMMMMMMMMMb, 4MMMMM';!!!!!!!!!!!!!!!' JMP',dMMMMMM
 4MP nMMM' '!!! uMMP MMMMMMMMMMMMMMMM,`MMM' !!!!!!!!!!!!!!! ;MMP dMMMMMMMM
 .,nMP".,= !!!! MMM dMMMMMMMMMMMMMMMMM M" ;!!!!!!!!!!!!!!! nMM".dMMMMMMMMM
MMMMM'- ;!!!!!!!!!!!!!!!' dMP ;MMMMMMMMMMM
,ndP".;!!!!!!!!!!;; `4MMMMMMMMMMMMMP' ;<!!!!!!!!!!!!!!! -P" ,dMMMMMMMMMMMM
"',;!!!!!!!!!!!!!!!!>;.`"4MMMMMMP" ;!!!!!!!!!!!!!!!'` ,xndMMMMMMMMMMMMMMMM
!!!!!!!!!!!!!!!!!!!!!!!!!;;,..,,;<!!!!!!!!!!!''''.,nMMMMMMMMMMMMMMMMMMMMMM
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!' ,xnnMMMMMMMMMMMMMMMMMMMMMMMMMMMM
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!'''.,,xn,`"4MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
!!!!!!!!!!!!!!!!!!!!!!!!!'''.,xnnMMMMMMMMMMn,`4MMMMMMMMMMMMMMMMMMMMMMMMMMM
!!!!!!!!!!!!!!!!!!!!!''` -dMMMMMMMMMMMMMMMMMMM,"4MMMMMMMMMMMMMMMMMMMMMMMMM
```''!!!!!!''''''.,,nMMMMx "MMMMMMMMMMMMMMMMMMMMx.""44MMMMMMMMMMMMMMMMMMMM
MMMmn,..,xmnmdMMMMMMMMMMMMMnx."4MMMMMMMMMMMMMMMMMMMMn.`"4MMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb,."4MMMMMMMMMMMMMMMMMMMMMx."4MMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMbx."44MMMMMMMMMMMMMMMMMMMMx "4MMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMn.""4MMMMMMMMMMMMMMMMMMbx "4MMMMMMMM
        */

    //endregion

        for (EndPoint endpoint : endpoints) {
            for (Query query : endpoint.getQueries()) {
                if (query.getVideo().equals(video)) {
                    for (Cache cache : caches) {
                        for (Connection connection : connections) {
                            if (cache.getId() == connection.getIdCache() && endpoint.getId() == connection.getIdEndPoint()) {
                                listVideosInCache.add(video);
                                videosToCache.put(cache, listVideosInCache);
                            }
                        }
                    }
                }
            }
        }
    }

    public double estimateScore(DataBundle data) {
        double score;
        double totalNumberOfRequests = 0.0;
        double sumOfAllTimeSavedForOneRequest = 0.0;
        double numberOfRequestsForOneEndpoint;
        double datacenterLatency;
        double cacheLatency;
        for (EndPoint endpoint : data.getEndPoints()) {
            numberOfRequestsForOneEndpoint = endpoint.getQueries().size();
            datacenterLatency = endpoint.getDataCenterLatency();
            cacheLatency = 0.0;
            for (Connection connection : data.getConnections()) {
                if (connection.getIdEndPoint() == endpoint.getId()) {
                    cacheLatency = connection.getLatency();
                    break;
                }
            }
            sumOfAllTimeSavedForOneRequest += numberOfRequestsForOneEndpoint * (datacenterLatency - cacheLatency);
            totalNumberOfRequests += numberOfRequestsForOneEndpoint;
        }
        sumOfAllTimeSavedForOneRequest *= 1000.0;
        score = sumOfAllTimeSavedForOneRequest / totalNumberOfRequests;

        return score;
    }
}
