/*
 * Copyright (C) 2014 kevin
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package reseau.server;

import reseau.common.Constant;

/**
 *
 * @author kevin
 */
public class SchedulerLeaveCtrl extends Thread {
    private long startTime, endTime, duration;
    private Room room;
    private ClientManager client;
    
    public SchedulerLeaveCtrl(Room room, ClientManager client) {
        this.room = room;
        this.client = client;
        startTime = System.nanoTime();
    }
    
    @Override
    public void run() {
        boolean cont = true;
        while (cont) {
            endTime  = System.nanoTime();
            duration = (endTime - startTime);
                if ((duration / 1000000000) > 30) {

                    /* Retirer le controle au bout de 30 secondes. */
                    this.startTime = -1;
                    client.leaveCtrl();
                    room.leaveWaitList(client);
                    cont = false;
                }
        }
    }
}
