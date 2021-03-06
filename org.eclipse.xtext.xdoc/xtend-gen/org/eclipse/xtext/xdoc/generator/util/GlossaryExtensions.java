package org.eclipse.xtext.xdoc.generator.util;

import com.google.common.base.Objects;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xdoc.xdoc.GlossaryEntry;

@SuppressWarnings("all")
public class GlossaryExtensions {
  public GlossaryEntry getEntry(final Iterable<GlossaryEntry> glossary, final String aliasOrName) {
    GlossaryEntry _xblockexpression = null;
    {
      final Function1<GlossaryEntry, Boolean> _function = (GlossaryEntry s) -> {
        String _name = s.getName();
        return Boolean.valueOf(Objects.equal(_name, aliasOrName));
      };
      GlossaryEntry matchedEntry = IterableExtensions.<GlossaryEntry>findFirst(glossary, _function);
      GlossaryEntry _xifexpression = null;
      boolean _equals = Objects.equal(matchedEntry, null);
      if (_equals) {
        final Function1<GlossaryEntry, Boolean> _function_1 = (GlossaryEntry s) -> {
          return Boolean.valueOf(s.getAlias().contains(aliasOrName));
        };
        _xifexpression = IterableExtensions.<GlossaryEntry>findFirst(glossary, _function_1);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
