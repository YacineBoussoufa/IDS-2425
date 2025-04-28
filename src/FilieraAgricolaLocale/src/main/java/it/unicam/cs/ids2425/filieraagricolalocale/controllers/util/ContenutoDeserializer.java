package it.unicam.cs.ids2425.filieraagricolalocale.controllers.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pacchetto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;

public class ContenutoDeserializer extends StdDeserializer<Contenuto>{

   public ContenutoDeserializer() {
      this(null);
   }

   public ContenutoDeserializer(final Class<?> vc) {
      super(vc);
   }

   @Override
   public Contenuto deserialize(final JsonParser parser, final DeserializationContext context)
   throws IOException, JsonProcessingException {

      final JsonNode node = parser.getCodec().readTree(parser);
      final ObjectMapper mapper = (ObjectMapper)parser.getCodec();
      if (node.has("ingredienti")) {
            return mapper.treeToValue(node, Prodotto.class);
      } else {
            return mapper.treeToValue(node, Pacchetto.class);
      }  
   }
       
}
