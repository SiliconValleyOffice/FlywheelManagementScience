/* @(#)FlywheelCommunityAuthentication.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
** All other rights are reserved.
**
** gConGUI (game Controller Graphical User Interface) is an exclusive
** trademark of Steven D. Stamps.  This trademark may be used freely
** within the unforked FlywheelMS application and documentation.
** All other rights are reserved.
**
** Trademark information is available at
** <http://www.flywheelms.com/trademarks>
**
** Flywheel Management Science(TM) is a copyrighted body of management
** metaphors, governance processes, and leadership techniques that is
** owned by Steven D. Stamps.  These copyrighted materials may be freely
** used, without alteration, by the community (users and developers)
** surrounding this GPL3-licensed software.  Additional copyright
** information is available at <http://www.flywheelms.org/copyrights>
**
**              GPL3 Software License
** This program is free software: you can use it, redistribute it and/or
** modify it under the terms of the GNU General Public License, version 3,
** as published by the Free Software Foundation. This program is distributed
** in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
** even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
** PURPOSE.  See the GNU General Public License for more details. You should
** have received a copy of the GNU General Public License, in a file named
** COPYING, along with this program.  If you cannot find your copy, see
** <http://www.gnu.org/licenses/gpl-3.0.html>.
*/

package com.flywheelms.library.fca;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.enumerator.FcaAuthority;
import com.flywheelms.library.fca.util.FcaUserCredentials;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;

public class FlywheelCommunityAuthentication {
	
	private FcaUserCredentials fcaUserCredentials = getMockCredentials();
//	private Hashtable<String, CommunityMember> communityMemberCache = new Hashtable<String, CommunityMember>();
			
	private static FlywheelCommunityAuthentication singleton;
	
	private FlywheelCommunityAuthentication() {
//		initializeCache();
	}
	
//	private void initializeCache() {
//		this.communityMemberCache.put(CommunityMember.getNullValue().getNodeIdString(), CommunityMember.getNullValue());
//		CommunityMember theSteveStamps = new CommunityMember("CMR-1");
//		theSteveStamps.setGivenName("Steve");
//		theSteveStamps.setFamilyName("Stamps");
//		this.communityMemberCache.put(theSteveStamps.getNodeIdString(), theSteveStamps);
//		CommunityMember theAdamDye = new CommunityMember("CMR-2");
//		theAdamDye.setGivenName("Adam");
//		theAdamDye.setFamilyName("Dye");
//		this.communityMemberCache.put(theAdamDye.getNodeIdString(), theAdamDye);
//		CommunityMember theJonHaarmann = new CommunityMember("CMR-3");
//		theJonHaarmann.setGivenName("Jon");
//		theJonHaarmann.setFamilyName("Haarmann");
//		this.communityMemberCache.put(theJonHaarmann.getNodeIdString(), theJonHaarmann);
//		CommunityMember theJessicaKerr = new CommunityMember("CMR-4");
//		theJessicaKerr.setGivenName("Jessica");
//		theJessicaKerr.setFamilyName("Kerr");
//		this.communityMemberCache.put(theJessicaKerr.getNodeIdString(), theJessicaKerr);
//		CommunityMember theLauriPeterson = new CommunityMember("CMR-5");
//		theLauriPeterson.setGivenName("Lauri");
//		theLauriPeterson.setFamilyName("Peterson");
//		this.communityMemberCache.put(theLauriPeterson.getNodeIdString(), theLauriPeterson);
//		CommunityMember theJonTate = new CommunityMember("CMR-6");
//		theJonTate.setGivenName("Jon");
//		theJonTate.setFamilyName("Tait");
//		this.communityMemberCache.put(theJonTate.getNodeIdString(), theJonTate);
//	}

	public static FlywheelCommunityAuthentication getInstance() {
		if(FlywheelCommunityAuthentication.singleton == null) {
			FlywheelCommunityAuthentication.singleton = new FlywheelCommunityAuthentication();
		}
		return FlywheelCommunityAuthentication.singleton;
	}
	
	public FcaUserCredentials getFcaUserCredentials() {
/**
 * if(fcaUserCredentials.getLastAuthentication() > someLimitedPeriodOfTime) {
 * 		re-authenticate and throw an exception if unable
 * }
 */
		return this.fcaUserCredentials;
	}
	
	public static FcaUserCredentials getNullCredentials() {
		// TODO - should validate and etc...
		return new FcaUserCredentials(
				FcaAuthority.NONE,
				false,
				CommunityMember.getNullValue().getNodeIdString(),
				GcgDateHelper.getCurrentDateTime() );
	}
	
	public static FcaUserCredentials getMockCredentials() {
		return new FcaUserCredentials(
				FcaAuthority.NONE,
				false,
				"CMR-11111111-1111-1111-1111-111111111111",
				GcgDateHelper.getCurrentDateTime() );
	}
	
	public CommunityMember getCommunityMember(FcaUserCredentials aUserCredentials) {
		return getCommunityMember(aUserCredentials.getCommunityMemberNodeIdString());
	}

	public CommunityMember getCommunityMember(String aNodeIdString) {
		CommunityMember theCommunityMember;
		// if we have an FMM,
		// get the CommunityyMember from the database mediator,
		// else get it from the FSE document
		// else "redact" the name from the document  ??
		theCommunityMember = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(aNodeIdString);
//		if(theCommunityMember == null) {
//			theCommunityMember = new CommunityMember(aNodeIdString);
//			theCommunityMember.setGivenName("Redacted");
//			theCommunityMember.setFamilyName("Redacted");
//			this.communityMemberCache.put(aNodeIdString, theCommunityMember);
//		}
		return theCommunityMember;
	}

	public CommunityMember getCommunityMember() {
		return getCommunityMember(getFcaUserCredentials());
	}

}
