package br.edu.utfpr.reportgenerator.service;

import br.edu.utfpr.reportgenerator.model.ImageSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageSetService {

    private static final List<ImageSet> AVAILABLE_SETS = List.of(
            new ImageSet(
                    "sisu",
                    "Tema SiSU",
                    "images/logos/sisu.png",
                    "images/logos/utfpr.png"
            ),
            new ImageSet(
                    "vestibular",
                    "Tema Vestibular",
                    "images/logos/vestibular.png",
                    "images/logos/utfpr.png"
            ),
            new ImageSet(
                    "maisenem",
                    "Tema +Enem UTFPR",
                    "images/logos/maisenem.png",
                    "images/logos/utfpr.png"
            ),
            new ImageSet(
                    "pss",
                    "Tema PSS",
                    "images/logos/pss.png",
                    "images/logos/utfpr.png"
            )
    );

    /**
     * Retorna todos os conjuntos de imagens disponíveis para seleção.
     * @return Uma lista de ImageSet.
     */
    public List<ImageSet> getAvailableImageSets() {
        return AVAILABLE_SETS;
    }

    /**
     * Busca um conjunto de imagens específico pelo seu ID.
     * @param id O ID do conjunto a ser encontrado.
     * @return Um Optional contendo o ImageSet se encontrado, ou vazio caso contrário.
     */
    public Optional<ImageSet> findById(String id) {
        return AVAILABLE_SETS.stream()
                .filter(set -> set.id().equals(id))
                .findFirst();
    }
}