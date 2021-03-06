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
package com.left8.evs.edmodule.peakfinding;

/**
 *
 * @author  Lefteris Paraskevas
 * @version 2016.03.16_1212
 * @param <Start>
 * @param <End>
 */
public class Window<Start,End> {
    
    private Start start;
    private End end;
    
    /**
     * Public constructor
     * @param start
     * @param end 
     */
    public Window(Start start, End end){
        this.start = start;
        this.end = end;
    }
    
    public Start getStart() { return start; }
    
    public End getEnd() { return end; }
    
    public void setStart(Start start) { this.start = start; }
    
    public void setEnd(End end) { this.end = end; }
    
    public void swapEdges() {
        Start temp;
        temp = start;
        start = (Start) end;
        end = (End) temp;
    }
}
