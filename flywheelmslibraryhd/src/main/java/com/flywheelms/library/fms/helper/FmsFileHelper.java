/* @(#)FmsFileHelper.java
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

package com.flywheelms.library.fms.helper;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.os.Environment;

import com.flywheelms.library.gcg.GcgApplication;

public class FmsFileHelper {

	public static File writeFileToDownloadsDirectory(String aBaseFileNameWithExtension, ByteArrayOutputStream aByteArrayOutputStream) throws IOException  {
		return writeExternalFile(aBaseFileNameWithExtension, aByteArrayOutputStream, Environment.DIRECTORY_DOWNLOADS);
	}

	public static File writeExternalFile(String aBaseFileNameWithExtension, ByteArrayOutputStream aByteArrayOutputStream, String aDirectoryName) throws IOException  {
		File theDirFile = GcgApplication.getContext().getExternalFilesDir(aDirectoryName);
		String theFilePath = theDirFile.getAbsolutePath() + "/" + aBaseFileNameWithExtension;
		File theFile = new File(theFilePath);
		FileOutputStream theFileOutputStream = new FileOutputStream(theFile);
		aByteArrayOutputStream.writeTo(theFileOutputStream);
		theFileOutputStream.flush();
		theFileOutputStream.close();
		//		GcgHelper.makeToast("Successfully created " + theFilePath);
		return theFile;
	}

	@SuppressWarnings("resource")
	public static void writeApplicationFile(String aBaseFileNameWithExtension, String aStringToWrite) {
		FileOutputStream outputStream = null;
		try {
			outputStream = GcgApplication.getContext().openFileOutput(aBaseFileNameWithExtension, Context.MODE_PRIVATE);
			outputStream.write(aStringToWrite.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static File getDatabaseFile(String aFileName) {
		return GcgApplication.getContext().getDatabasePath(aFileName);
	}

	@SuppressWarnings("resource")
	public static boolean copyFile(File aSourceFile, File aDestinationFile) {
		Boolean bResults = true;
		FileInputStream theFileInputStream = null;
		FileOutputStream theFileOutputStream = null;
		FileChannel theFileChannelIn = null;
		FileChannel theFileChannelOut = null;
		try {
			theFileInputStream = new FileInputStream(aSourceFile);
			theFileOutputStream = new FileOutputStream(aDestinationFile);
			theFileChannelIn = theFileInputStream.getChannel();
			theFileChannelOut = theFileOutputStream.getChannel();
			theFileChannelIn.transferTo(0, theFileChannelIn.size(), theFileChannelOut);
		} catch (FileNotFoundException e) {
			bResults = false;
			e.printStackTrace();
		} catch (IOException e) {
			bResults = false;
			e.printStackTrace();
		} finally {
			try {
				if(theFileInputStream != null) {
					theFileInputStream.close();
				}
				if(theFileOutputStream != null) {
					theFileOutputStream.close();
				}
				if(theFileChannelIn != null) {
					theFileChannelIn.close();
				}
				if(theFileChannelOut != null) {
					theFileChannelOut.close();
				}
			} catch (IOException e) {
				bResults = false;
				e.printStackTrace();
			}
		}
		return bResults;
	}

	public static boolean applicationFileExists(String aBaseFileNameWithExtension) {
		File theFile = new File(GcgApplication.getContext().getFilesDir(), aBaseFileNameWithExtension);
		return theFile.exists();   
	}

	public static boolean dbFileExists(String aBaseFileNameWithExtension) {
		File dbFile=GcgApplication.getContext().getDatabasePath(aBaseFileNameWithExtension);
		return dbFile.exists();
	}

	@SuppressWarnings("resource")
	public static String readStringFromApplicationFile(String aBaseFileNameWithExtension) {
		if(! applicationFileExists(aBaseFileNameWithExtension)) {
			return "";
		}
		StringBuilder theStringBuilder = new StringBuilder();
		InputStream theInputStream = null;
		try {

			BufferedReader theBufferedReader = null;
			theInputStream = GcgApplication.getContext().openFileInput(aBaseFileNameWithExtension);
			theBufferedReader = new BufferedReader(new InputStreamReader(theInputStream));
			String theLine;
			while ((theLine = theBufferedReader.readLine()) != null) {
				theStringBuilder.append(theLine);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (theInputStream != null) {
				try {
					theInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return theStringBuilder.toString();
	}

}
