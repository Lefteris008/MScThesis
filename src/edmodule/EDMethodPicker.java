/*
 * Copyright (C) 2016 Lefteris Paraskevas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edmodule;

import edmodule.data.Dataset;
import edmodule.data.EDCoWCorpus;
import edmodule.data.PeakFindingCorpus;
import edmodule.edcow.EDCoW;
import edmodule.lsh.LSH;
import edmodule.peakfinding.BinPair;
import edmodule.peakfinding.BinsCreator;
import edmodule.peakfinding.OfflinePeakFinding;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import utilities.Config;
import utilities.Utilities;

/**
 *
 * @author  Lefteris Paraskevas
 * @version 2016.01.25_1655_gargantua
 */
public class EDMethodPicker {
    
    /**
     * Pick method of Event Detection.
     * @param config A configuration object
     */
    public static void selectEDMethod(Config config) throws FileNotFoundException {
        System.out.println("\nPick a method for Event Detection");
        System.out.println("1. EDCoW");
        System.out.println("2. LSH");
        System.out.println("3. Offline Peak Finding");
        System.out.print("Your choice: ");
        
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        
        switch(choice) {
            case 1: {
                Dataset ds = new Dataset(config);
                EDCoWCorpus corpus = new EDCoWCorpus(config, ds.getTweetList(), ds.getSWH());
                corpus.createCorpus();
                corpus.setDocTermFreqIdList();
                
                EDCoW edcow = new EDCoW(4, 11, 5, 0.001, 0.1, 1, 155, corpus); //Create the EDCoW object
                Utilities.printMessageln("Selected method: " + edcow.getName());
                Utilities.printMessageln("Started applying algorithm...");
                edcow.apply(); //Apply the algorithm
                Utilities.printMessageln("Succesfully applied EDCoW algorithm");
                break;
            } case 2: {
                Utilities.printMessageln("LSH not implemented yet!");
                break;
            } case 3: {
                int window = 10;
                Dataset ds = new Dataset(config);
                PeakFindingCorpus corpus = new PeakFindingCorpus(config, ds.getTweetList(), ds.getSWH());
                List<BinPair<String, Integer>> bins = BinsCreator.createBins(corpus, config, window);
                OfflinePeakFinding opf = new OfflinePeakFinding(bins, 0.999, 1, 5, window, corpus);
                Utilities.printMessageln("Selected method: " + opf.getName());
                Utilities.printMessageln("Now applying algorithm...");
                opf.apply();
                //opf.printEventsStatistics();
                break;
            } default: {
                System.out.println("No method selected. Exiting now...");
                System.exit(0);
            }
        }
    }
}
