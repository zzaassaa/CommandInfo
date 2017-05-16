package com.draganz.commandinfo.api;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslationFormatException;
import net.minecraft.util.text.translation.I18n;

/**
 * A simple, quick custom chat component (ITextComponent) that can be used to implements the default formatting
 * 
 * @author draganz
 *
 */
public class CommentComponent extends TextComponentBase{

	private final String COMMENT;
	private ITextComponent child;
    private final Object LOCK = new Object();
    private long lastTranslationUpdateTimeInMilliseconds = -1L;

    public CommentComponent( final String translationKey ){
    	COMMENT = translationKey;
    }

    
    synchronized void ensureInitialized(){
    	
        synchronized (LOCK){
            long i = I18n.getLastTranslationUpdateTimeInMilliseconds();

            if (i == this.lastTranslationUpdateTimeInMilliseconds){
                return;
            }

            this.lastTranslationUpdateTimeInMilliseconds = i;
            child = null;
        }

        try{
            this.initializeFromFormat(I18n.translateToLocal(COMMENT));
        }catch (TextComponentTranslationFormatException textcomponenttranslationformatexception){
        	child = null;

            try{
                this.initializeFromFormat(I18n.translateToFallback(COMMENT));
            }
            catch (TextComponentTranslationFormatException var5){
                throw textcomponenttranslationformatexception;
            }
        }
    }

    
    protected void initializeFromFormat( final String format ){
    	final TextComponentString textComp = new TextComponentString(getSpecialFormat(format));
    	textComp.getStyle().setParentStyle(getStyle());
    	child = textComp;
    }
    
    /*
     * This methods applied the special format codes to help make the default writing scheme easier
     * ===========================================================================================
     * Format code   |   Equivalent   |   Description
     * ==================================FORMAT===================================================
     * @N  |    "§e"         |    Used for specifying the command name; should be used in conjunction with @R
     * \n  |    "\n"         |    Default new line usage, provided by java, must have #PARSE_ESCAPES defined alone on the first line
     * @U  | "§a[USAGE]§r "  |    Used for specifying the usage, should be like the .usage
     * @O  |   "[§o"         |    Used in the usage section to specify (beginning, see @RO) an optional parameter
     * @A  | "§dArgs§r: §b"  |    Used to specify the beginning of an argument (Arg), should be immediately by a parameter
     * @S  | "§5Result§r: §6Success ->§r " |  Used to specify the success result
     * @F  | "§5Result§r: §6Fail ->§r "    |  Used to specify the fail result
     * @B  | "§5Result§r: "                |  Used to specify the result for a defiant success (always succeeds)
     * @E  |  "Ex: §c"       |    Used to specify the beginning of an example, but be ended using @R
     * @R  |   "§r"          |    Used to end a text formatting statement
     * @T  |    "§r]"        |    Used to end an optional parameter in the usage section (see @O)
     * @Y  |   " ->§r "      |    Used to end the parameter specified by the argument (see @A)
     * @I  |   " (Optional) ->§r "         |  Used to end the optional parameter specified by the argument (see @A and @O)
     * @P  |    "§b"         |    Used to specify the beginning of a parameter; used if trying to define it outside of the argument section, must be ended with either @R (or §r)
     * @C  |    "§c"         |    Used to specify the beginning of a command; used if trying to define or exemplify it outside of the example section, must be ended with either @r (or §r)
     *
     */
    private String getSpecialFormat( final String toFormat ){
    	return StringUtils.replaceEach(toFormat, 
    	   		new String[]{"@N", "@R", "@U","@O", "@T", "@A", "@Y","@I","@S","@B","@F","@P","@E","@C"}, 
    	    	new String[]{"§e","§r","§a[USAGE]§r ","[§o","§r]","§dArgs§r: §b"," ->§r ",
    	    				 " (Optional) ->§r ","§5Result§r: §6Success ->§r ","§5Result§r: ","§5Result§r: §6Fail ->§r ",
    	    				 "§b","Ex: §c","§c"}
    	);
    }
    
    
    @Override
    public Iterator<ITextComponent> iterator(){
        this.ensureInitialized();
        return Iterators.<ITextComponent>concat(Iterators.<ITextComponent>forArray(new CommentComponent[] {this}), 
        		createDeepCopyIterator(this.siblings));
    }
    
    
    @Override
    public String getUnformattedComponentText(){
    	this.ensureInitialized();
    	return child.getUnformattedComponentText();
    }

    
    @Override
    public boolean equals(Object input){

        if (this == input){
            return true;
        }else if (!(input instanceof CommentComponent)){
            return false;
        }else{
            CommentComponent textcomponenttranslation = (CommentComponent)input;
            return COMMENT.equals(textcomponenttranslation.COMMENT) && super.equals(input);
        }
    }
    
    public ITextComponent setStyle( final Style style ){
        super.setStyle(style);

        if (this.lastTranslationUpdateTimeInMilliseconds > -1L){
        	child.getStyle().setParentStyle(style);
        }

        return this;
    }

    @Override
    public int hashCode(){
        int i = super.hashCode();
        i = 31 * i + COMMENT.hashCode();
        return i;
    }

    @Override
    public String toString(){
        return "TextInfoComponents{key=\'".concat(COMMENT).concat("\'").concat(", style=") + this.getStyle() + '}';
    }

    
    public String getComment(){
        return COMMENT;
    }


	@Override
	public ITextComponent createCopy() {
		final CommentComponent txtComp = new CommentComponent(COMMENT);
		txtComp.setStyle(this.getStyle().createShallowCopy());
		
		for(ITextComponent out : this.getSiblings()){
			txtComp.appendSibling(out.createCopy());
		}
		
		return txtComp;
	}

}
