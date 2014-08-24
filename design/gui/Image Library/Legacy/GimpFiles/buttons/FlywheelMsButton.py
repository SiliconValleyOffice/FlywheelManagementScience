#!/usr/bin/env python

##B&P Glossy Web 2.0 style Button 01
##Gimp Python plugin - Glossy Button 1
##Copyright (C) 2010 Andrei Roslovtsev ~ www.byes-and-pixels.com
##
##This program is free software; you can redistribute it and/or
##modify it under the terms of the GNU General Public License
##as published by the Free Software Foundation version 2
##of the License.
##
##This program is distributed in the hope that it will be useful,
##but WITHOUT ANY WARRANTY; without even the implied warranty of
##MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
##GNU General Public License for more details.
##
##You should have received a copy of the GNU General Public License
##along with this program; if not, write to the Free Software
##Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA

# Creates creates a glossy Web2.0 style button
# Accepted parameters are dimensions, color,
# corner radius, shadow amount, Add optional text - size, aFont, color,
# create button states, flatten resulting image(s).
#
# Works best with light colors and low saturation.
#
# Note:
# Developed and tested with Python 2.6 only.
#
# Version:1.0
# 2010-Jun-07
# Andrei Roslovtsev
# www.bytes-and-pixels.com
# andrei@bytes-and-pixels.com
# modified by Steven D. Stamps


#-----------------------------------------------------------
from gimpfu import *
import os
import re
import gtk

def make_many_buttons(
	rounding,
	shadow_amount,
	main_color,
	text_size,
	button_text,
	font,
	text_color_up,
	text_color_over,
	text_color_focus,
	text_color_down,
	flatten_flag,
	states_flag,
	directory_path,
	create_files_flag,
	all_buttons_flag ):

	if all_buttons_flag == 0:
		make_button(
			rounding,
			shadow_amount,
			main_color,
			text_size,
			button_text,
			font,
			text_color_up,
			text_color_over,
			text_color_focus,
			text_color_down,
			flatten_flag,
			states_flag,
			directory_path,
			create_files_flag )


	if all_buttons_flag == 1:
		button_list = ["Add", "Apply", "Back", "Browse", "Cancel", "Close", "Copy Lines", "Copy To Clipboard",
			"Create Directory", "Delete", "Edit", "Finish", "Last", "New", "No", "Ok", "Options Hide",
			"Options Show", "Preview", "Previous", "Properties", "Rename", "Save As", "Select", "Send",
			"Show Merged", "Submit", "Test", "Today", "Update", "Verify Connection", "Work Offline",
			"Work Online", "Yes"]
		for theButtonText in button_list:
			make_button(
				rounding,
				shadow_amount,
				main_color,
				text_size,
				theButtonText,
				font,
				text_color_up,
				text_color_over,
				text_color_focus,
				text_color_down,
				flatten_flag,
				states_flag,
				directory_path,
				create_files_flag )
			

def make_button(
	aRounding,
	aShadowAmount,
	aMainColor,
	aTextSize,
	aButtonText,
	aFont,
	aTextColorUp,
	aTextColorOver,
	aTextColorFocus,
	aTextColorDown,
	aFlattenFlag,
	aStatesFlag,
	aDirectoryPath,
	aCreateFilesFlag ):

    if aCreateFilesFlag == 1:
        aFlattenFlag = 1
        aStatesFlag = 1

    if aFont==None:
        aFont='Sans'

    end_width = 10
    text_width = len(aButtonText)
    width_var = (text_width * 7) + (end_width * 2)
    height_var = 28

    pdb.gimp_context_set_background((189,180,165))
        
    # define margin width to accomodate shadow
    margin = aShadowAmount*2

    if (margin<4):
        margin=4
    
    # set image dimensions
    img_w = width_var+margin*2
    img_h = height_var+margin*2
        
    up_image = pdb.gimp_image_new(img_w, img_h, 0)
    pdb.gimp_image_set_filename(up_image, "up")
    
    layer1 = pdb.gimp_layer_new(up_image, img_w, img_h, 1, 'BG', 100, 0)
    layer2 = pdb.gimp_layer_new(up_image, img_w, img_h, 1, 'Base Color', 100, 0)
    pdb.gimp_image_add_layer(up_image, layer1, 0)
    pdb.gimp_image_add_layer(up_image, layer2,-1)
    gimp.pdb.gimp_edit_fill(layer1, 3)

    pdb.gimp_round_rect_select(up_image, margin, margin, width_var,
                               height_var, aRounding, aRounding, 2, 1, 0, 1, 1)

    pdb.gimp_context_set_foreground(aMainColor)
    
    #pdb.gimp_bucket_fill(drawable, fill_mode, paint_mode, opacity, threshold, sample_merged, x, y)
    pdb.gimp_bucket_fill(layer2, 0, 0, 100, 0, 0, 0, 0)

    #Save original selection (shape of the button)
    orig_shape = pdb.gimp_selection_save(up_image)
    
    #get feather amount based on button dimensions
    if width_var-height_var<0:
        feather=width_var-(width_var/10)
    else:
        feather=height_var-(height_var/10)
    #gimp.pdb.gimp_message(feather)
    
    pdb.gimp_selection_feather(up_image, feather)
    pdb.gimp_edit_clear(layer2)

    layer2_tmp1=pdb.gimp_layer_copy(layer2, 1)
    layer2_tmp2=pdb.gimp_layer_copy(layer2, 1)
    pdb.gimp_image_add_layer(up_image, layer2_tmp1,-2)
    pdb.gimp_image_add_layer(up_image, layer2_tmp2,-3)

    #merge duplicated layers
    pdb.gimp_layer_set_visible(layer1, 0)
    layer2 = pdb.gimp_image_merge_visible_layers(up_image, 0)
    #pdb.gimp_layer_set_visible(layer1, 1)
    
    #load original shape selection
    pdb.gimp_selection_load(orig_shape)

#Add matte layer --------------------------------------------------------
    pdb.gimp_context_set_background((189,180,165))
    layer_matte = pdb.gimp_layer_new(up_image, img_w, img_h, 1, 'Matte', 100, 0)
    pdb.gimp_image_add_layer(up_image, layer_matte, 1)
    pdb.gimp_selection_load(orig_shape)

    pdb.gimp_edit_bucket_fill(layer_matte, 1, 0, 100, 0, 0, 0, 0)

    
#Add reflection ---------------------------------------------------------


    #pdb.gimp_ellipse_select(up_image, x, y, width, height, operation, antialias, feather, feather_radius)
    pdb.gimp_ellipse_select(up_image, img_w*-1, img_h/2, img_w*3, (width_var+height_var)/2, 3, 1, 0, 0)

    layer3 = pdb.gimp_layer_new(up_image, img_w, img_h, 1, 'Reflection', 25, 3)
    pdb.gimp_image_add_layer(up_image, layer3, 0)

    #pdb.gimp_selection_shrink(up_image, 10)

    pdb.gimp_context_set_default_colors()
    
    #pdb.gimp_context_set_gradient('FG to Transparent')
    pdb.gimp_edit_blend(layer3, 2, 0, 0, 70, 0, 0, 0, 0,
                        0, 0, 1, img_w/2, img_h,
                        img_w/2, img_h/3)

# Specular 1 layer -----------------------------------------------------------    
    
    layer4 = pdb.gimp_layer_new(up_image, img_w, img_h, 1, 'Specular', 100, 0)
    pdb.gimp_image_add_layer(up_image, layer4, 0)

    #load original shape selection
    pdb.gimp_selection_load(orig_shape)
    pdb.gimp_selection_shrink(up_image, margin*0.3)
    pdb.gimp_rect_select(up_image,0,margin+margin*0.6,img_w+margin*5,
                         img_h+margin*3,1, 0, 0)


    pdb.gimp_context_swap_colors()
    
    pdb.gimp_edit_blend(layer4, 2, 0, 1, 100, 0, 0, 0, 0,
                        0, 0, 1, img_w/2, 0, 10, 0)
    #                    0, 0, 1, img_w/2, 0, aRounding*2, 0)
    
    pdb.gimp_context_swap_colors()
    pdb.gimp_selection_none(up_image)

    pdb.plug_in_gauss(up_image, layer4, width_var*0.02, height_var*0.01, 0)

# Shadow layer ---------------------------------------------------------------
    if (aShadowAmount!=0):
        
        layer5 = pdb.gimp_layer_new(up_image, img_w, img_h, 1, 'Shadow', 75, 0)
        pdb.gimp_image_add_layer(up_image, layer5, 3)

        #load original shape selection
        pdb.gimp_selection_load(orig_shape)

        pdb.gimp_selection_border(up_image, aShadowAmount)

        pdb.gimp_bucket_fill(layer5, 0, 0, 100, 0, 0, 0, 0)

        pdb.gimp_selection_none(up_image)

        # Do blur operation a few times depending on aShadowAmount
        i=0
        while (i<aShadowAmount):
            # set limit for itererations
            if (aShadowAmount<10):
                pdb.plug_in_blur(up_image, layer5)
            i=i+1
        
#Reflection 2 layer (window frame)--------------------------------------------
    
    layer6 = pdb.gimp_layer_new(up_image, img_w, img_h,
                                1, 'Window Refl', 30, 4)
    pdb.gimp_image_add_layer(up_image, layer6, 0)

    pdb.gimp_selection_all(up_image)
    pdb.gimp_rect_select(up_image, img_w/2-margin/2, 0, margin,
                         img_h, 1, 0, 0)
    pdb.gimp_rect_select(up_image, 0, img_h/2-margin/2, img_w,
                         margin, 1, 0, 0)

    #pdb.gimp_bucket_fill(drawable, fill_mode, paint_mode, opacity, threshold, sample_merged, x, y)
    pdb.gimp_bucket_fill(layer6, 1, 0, 100, 0, 0, 0, 0)

    pdb.gimp_selection_none(up_image)
    
    pdb.gimp_layer_scale(layer6, img_w/3, img_h/3, 0)
    pdb.gimp_layer_translate(layer6, img_w/2-aRounding/2, margin*1.2)

    pdb.plug_in_lens_distortion(up_image, layer6, 100, 80,
                                50, 0, 0, 0)
    pdb.gimp_layer_resize_to_image_size(layer6)
    pdb.gimp_layer_set_visible(layer6, 0)

# Text Layer --------------------------------------------
    layer7 = pdb.gimp_text_layer_new(up_image, aButtonText, aFont, aTextSize, 0)
    pdb.gimp_image_add_layer(up_image, layer7, 0)
    pdb.gimp_text_layer_set_color(layer7, aTextColorUp)
        
    txt_x = (up_image.width-layer7.width)/2
    txt_y = (up_image.height-layer7.height)/2
    layer7.set_offsets(txt_x, txt_y)

# Create state images -----------------------------------------------
    if aStatesFlag == 1:
        over_image = pdb.gimp_image_duplicate(up_image)
        down_image = pdb.gimp_image_duplicate(up_image)
        focus_image = pdb.gimp_image_duplicate(up_image)

        pdb.gimp_image_set_filename(over_image, "over")
        pdb.gimp_image_set_filename(down_image, "down")
        pdb.gimp_image_set_filename(focus_image, "focus")

        # Edit "Over" state
        o_layer5 = find_layer_by_name(over_image,"Shadow")
        o_layer6 = find_layer_by_name(over_image,"Window Refl")
        o_layer3 = find_layer_by_name(over_image,"Reflection")
        o_layer7 = find_layer_by_name(over_image, aButtonText)
        
        pdb.gimp_layer_set_opacity(o_layer5, 90) #shadow layer
        pdb.gimp_layer_set_opacity(o_layer6, 80) #window refl layer
        pdb.gimp_layer_set_opacity(o_layer3, 50) # refl layer
    	pdb.gimp_text_layer_set_color(o_layer7, aTextColorOver)


        # Edit "Down" state
        d_layer4 = find_layer_by_name(down_image,"Base Color")
        d_layer7 = find_layer_by_name(down_image, aButtonText)

        pdb.gimp_image_set_active_layer(down_image, d_layer4)
        pdb.gimp_hue_saturation( d_layer4, 0, -50, 0, -50)
    	pdb.gimp_text_layer_set_color(d_layer7, aTextColorDown)


        # Edit "Focus" state
        f_layer5 = find_layer_by_name(focus_image,"Shadow")
        f_layer7 = find_layer_by_name(focus_image, aButtonText)

    	pdb.gimp_layer_set_visible(f_layer5, 0)
    	pdb.gimp_text_layer_set_color(f_layer7, aTextColorFocus)

# Flatten Image routine ---------------------------------------------
    #if aFlattenFlag == 1 || aCreateFilesFlag == 1:
        pdb.gimp_image_remove_layer(up_image, layer1)
        layer1 = pdb.gimp_image_merge_visible_layers(up_image, 0)

	if aStatesFlag == 1:
        	pdb.gimp_image_remove_layer(over_image, find_layer_by_name(over_image,"BG"))
	        o_layer1 = pdb.gimp_image_merge_visible_layers(over_image, 0)

	        pdb.gimp_image_remove_layer(down_image, find_layer_by_name(down_image,"BG"))
        	d_layer1 = pdb.gimp_image_merge_visible_layers(down_image, 0)

	        pdb.gimp_image_remove_layer(focus_image, find_layer_by_name(focus_image,"BG"))
        	f_layer1 = pdb.gimp_image_merge_visible_layers(focus_image, 0)
        
    pdb.gimp_image_set_active_layer(up_image, layer1)

# Save Image Files  ------------------------------------------------------

    if aCreateFilesFlag == 0:
        display = pdb.gimp_display_new(up_image)
        if aStatesFlag == 1:
	      	display = pdb.gimp_display_new(over_image)
       		display = pdb.gimp_display_new(down_image)
	       	display = pdb.gimp_display_new(focus_image)

    if aCreateFilesFlag == 1:
    	up_filename = "up.png"
    	up_fullpath = build_full_filepath(aDirectoryPath, up_filename, aButtonText)
        pdb.file_png_save(up_image, layer1, up_fullpath, up_filename, 0, 9, 1, 1, 1, 1, 1)
	if aStatesFlag == 1:
    		over_filename = "over.png"
    		over_fullpath = build_full_filepath(aDirectoryPath, over_filename, aButtonText)
	       	pdb.file_png_save(over_image, o_layer1, over_fullpath, over_filename, 0, 9, 1, 1, 1, 1, 1)
    		down_filename = "down.png"
    		down_fullpath = build_full_filepath(aDirectoryPath, down_filename, aButtonText)
	        pdb.file_png_save(down_image, d_layer1, down_fullpath, down_filename, 0, 9, 1, 1, 1, 1, 1)
    		focus_filename = "focus.png"
    		focus_fullpath = build_full_filepath(aDirectoryPath, focus_filename, aButtonText)
	        pdb.file_png_save(focus_image, f_layer1, focus_fullpath, focus_filename, 0, 9, 1, 1, 1, 1, 1)

    
#--------------------------------------------------------------------
#Helper functions
#Author:  Joao S. O. Bueno
#source:  http://www.gimpusers.com/forums/gimp-user/
#    11870-set-active-layer-by-referring-to-its-name

def find_layer_by_name (anImage, aName):
    for theLayer in anImage.layers:
        if theLayer.name == aName:
            return theLayer
            return None


def build_full_filepath(aDirectoryPath, aFileName, aLabel_text):
	button_aDirectoryPath = os.path.join(aDirectoryPath, build_button_directory_name(aLabel_text))
	full_filepath = os.path.join(button_aDirectoryPath, aFileName);
	return full_filepath


def build_button_directory_name(aLabelText):
#	button_directory_name = re.sub('([\\\\/:*?"<>|]) ', "", aLabelText
	button_directory_name = re.sub(r" ", "", aLabelText)
	return button_directory_name


#---------------------------------------------------------------------
register(
    "FlywheelMS_Button",
    N_("Create a FlywheelMS button"),
    "Create a glossy FlywheelMS style button",
    "modified by Steven D. Stamps",
    "www.flywheelms.com",
    "2011",
    N_("_FlywheelMS button"),
    #"RGB*, GRAY*",
    "",
    [
        (PF_SPINNER, "rounding", "Corner Radius:", 10, (0, 100, 1)),
        (PF_SPINNER, "shadow_amount", "Shadow amount:", 2, (0, 100, 1)),
# a37e63  a27f63  5c4e42
        (PF_COLOR, "main_color", "Color:", (92, 78, 66) ),
        (PF_SPINNER, "text_size", "Text Size (px):", 12, (1, 2000, 1)),
        (PF_STRING, "button_text", "Text:", "Download"),
        (PF_FONT, "font", "FONT:", 0),
# 212121
        (PF_COLOR, "text_color_up", "Text Color - Up:", (33, 33, 33) ),
        (PF_COLOR, "text_color_over", "Text Color - Over:", (255, 255, 0) ),
        (PF_COLOR, "text_color_focus", "Text Color - Focus:", (238, 238, 222) ),
        (PF_COLOR, "text_color_down", "Text Color - Down:", (45, 251, 33) ),
        (PF_TOGGLE, "flatten_flag", "Flatten Image", 1),
        (PF_TOGGLE, "states_flag", "Generate Button States", 1),
        (PF_DIRNAME, "directory_path", "Directory Name:", ""),
        (PF_TOGGLE, "create_files_flag", "Create Files:", 0),
        (PF_TOGGLE, "all_buttons_flag", "Generate All Buttons", 0)
    ],
    [],
    make_many_buttons,
    menu="<Toolbox>/Xtns/FlywheelMS",
    domain=("gimp20-python", gimp.locale_directory)
    )

main()
