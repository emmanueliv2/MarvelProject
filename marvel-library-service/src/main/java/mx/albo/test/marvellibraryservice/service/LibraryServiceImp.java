package mx.albo.test.marvellibraryservice.service;

import mx.albo.test.marvellibraryservice.dao.CoWorkerRepository;
import mx.albo.test.marvellibraryservice.dao.MarvelCharacterRepository;
import mx.albo.test.marvellibraryservice.model.Comic;
import mx.albo.test.marvellibraryservice.model.Characters;
import mx.albo.test.marvellibraryservice.model.MarvelCharacter;
import mx.albo.test.marvellibraryservice.response.CreatorResponse;
import mx.albo.test.marvellibraryservice.response.InteractionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImp implements LibrarySevice {

	private static final Logger log = LoggerFactory.getLogger(LibraryServiceImp.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Autowired
	private MarvelCharacterRepository marvelCharacterRepository;

	@Autowired
	private CoWorkerRepository coWorkerRepository;

	@Override
	public ResponseEntity<?> getCreators(String name) {

		MarvelCharacter marvelCharacter = marvelCharacterRepository.findByName(name);
		if (marvelCharacter != null) {
			List<String> editors = null;
			List<String> writters = null;
			List<String> colorist = null;
			for (Comic comic : marvelCharacter.getComics()) {
				editors = comic.getCreators().stream()
						.filter(Creator -> Creator.getRol().equals("editor"))
						.distinct()
						.map(Creator -> Creator.getName())
						.collect(Collectors.toList());

				writters = comic.getCreators().stream()
						.filter(Creator -> Creator.getRol().equals("writer"))
						.distinct()
						.map(Creator -> Creator.getName())
						.collect(Collectors.toList());

				colorist = comic.getCreators().stream()
						.filter(Creator -> Creator.getRol().equals("inker"))
						.distinct()
						.map(Creator -> Creator.getName())
						.collect(Collectors.toList());
			}

			CreatorResponse creatorResponse = new CreatorResponse();
			creatorResponse.setLastSync(dateFormat.format(marvelCharacter.getLastSync()));
			creatorResponse.setEditors(editors);
			creatorResponse.setWritters(writters);
			creatorResponse.setColorist(colorist);
			return new ResponseEntity<CreatorResponse>(creatorResponse, null, HttpStatus.OK);

		} else
			return new ResponseEntity<String>("NOT FOUND", null, HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<?> getCoWorkers(String name) {
		MarvelCharacter marvelCharacter = marvelCharacterRepository.findByName(name);
		if (marvelCharacter != null) {
			List<String> namesCoWorkers = null;
			List<String> comics = null;
			Characters characters1 = null;
			List<Characters> characters = new ArrayList();
			for (Comic comic : marvelCharacter.getComics()) {
				namesCoWorkers = comic.getCoWorkers().stream()
						.distinct()
						.map(CoWorker -> CoWorker.getName())
						.collect(Collectors.toList());
			}
			for (String names: namesCoWorkers) {
				comics = coWorkerRepository.getComicsByIdCharacterAndCoWorker(marvelCharacter.getIdMarvelCharacter(), names);
				characters1 = new Characters();
				characters1.setNameCharacter(names);
				characters1.setComics(comics);
				characters.add(characters1);
			}
			InteractionResponse interactionResponse = new InteractionResponse();
			interactionResponse.setLastSync(dateFormat.format(marvelCharacter.getLastSync()));
			interactionResponse.setCharacters(characters);
			return new ResponseEntity<InteractionResponse>(interactionResponse, null, HttpStatus.OK);

		} else
			return new ResponseEntity<String>("NOT FOUND", null, HttpStatus.NOT_FOUND);
	}


}
