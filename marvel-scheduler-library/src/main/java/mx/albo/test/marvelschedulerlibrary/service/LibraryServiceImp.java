package mx.albo.test.marvelschedulerlibrary.service;

import mx.albo.test.marvelschedulerlibrary.dao.MarvelCharacterRepository;
import mx.albo.test.marvelschedulerlibrary.mapping.MarvelMappingService;
import mx.albo.test.marvelschedulerlibrary.model.CoWorker;
import mx.albo.test.marvelschedulerlibrary.model.Comic;
import mx.albo.test.marvelschedulerlibrary.model.Creator;
import mx.albo.test.marvelschedulerlibrary.model.MarvelCharacter;
import mx.albo.test.marvelschedulerlibrary.util.HashMD5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LibraryServiceImp implements LibrarySevice {

	@Value("${api.key.public}")
	private String apiKeyPublic;

	@Value("${api.key.private}")
	private String apiKeyPrivate;

	private String parameters;
	private String uri;

	@Autowired
	private MarvelMappingService mappingService;

	@Autowired
	private MarvelCharacterRepository marvelCharacterRepository;

	public static final String url = "https://gateway.marvel.com:443/v1/public/";
	private static final Logger log = LoggerFactory.getLogger(LibraryServiceImp.class);

	@Override
	public String updateLibrary(Date lastModified) {
		String input = lastModified.toString() + apiKeyPrivate + apiKeyPublic;
		String hashMd5 = HashMD5.getMd5(input);
		parameters = "?ts=" + lastModified + "&apikey=" + apiKeyPublic + "&hash=" + hashMd5;
		uri = "characters" + parameters;
		JSONObject jsonObject = mappingService.getMarvelResourceByUri(url + uri);

		//Initializer model
		MarvelCharacter marvelCharacter = null;
		Comic comic = null;
		List<Comic> comics = null;

		if (jsonObject != null) {
			try {
				jsonObject = jsonObject.getJSONObject("data");

				JSONArray jsonArray = jsonObject.getJSONArray("results");
				for(int i = 0; i < jsonArray.length(); i++) {
					marvelCharacter = new MarvelCharacter();
					JSONObject jsAux = jsonArray.getJSONObject(i);
					marvelCharacter.setIdMarvelCharacter(Integer.parseInt(jsAux.getString("id")));
					marvelCharacter.setName(jsAux.getString("name"));
					marvelCharacter.setResourceURI(jsAux.getString("resourceURI"));
					marvelCharacter.setLastSync(lastModified);

					//Obtain all comics by marvel character
					JSONObject jsonObjectComics = jsAux.getJSONObject("comics");
					JSONArray jsonArrayComics = jsonObjectComics.getJSONArray("items");
					comics = new ArrayList();
					for (int j = 0; j < jsonArrayComics.length(); j++){
						comic = new Comic();
						JSONObject jsAuxComic = jsonArrayComics.getJSONObject(j);
						comic.setResourceURI(jsAuxComic.getString("resourceURI"));
						comic.setName(jsAuxComic.getString("name"));
						//get comic info by resource_uri
						comic = getComicByUri(comic.getResourceURI(), comic);
						comic.setMarvelCharacter(marvelCharacter);
						comics.add(comic);

					}
					marvelCharacter.setComics(comics);
					marvelCharacterRepository.save(marvelCharacter);
					log.info("SAVED MARVEL ID CHARACTER {}", marvelCharacter.getIdMarvelCharacter());
				}
				log.info("UPDATE LIBRARY COMPLETE {}", lastModified);
				return "UPDATE LIBRARY COMPLETE";
			} catch (JSONException e) {
				log.error("ERROR {}", e);
				return "ERROR";
			}
		} else {
			log.info("NOT FOUND DATA {}", lastModified);
			return "NOT FOUND DATA";
		}
	}

	public Comic getComicByUri(String resourceUri, Comic comic){
		uri = resourceUri + parameters;
		JSONObject jsonObject = mappingService.getMarvelResourceByUri(uri);
		Creator creator = null;
		CoWorker coWorker = null;
		List<Creator> creators = null;
		List<CoWorker> coWorkers = null;
		if (jsonObject != null) {
			try {
				jsonObject = jsonObject.getJSONObject("data");
				JSONArray jsonArray = jsonObject.getJSONArray("results");

				JSONObject jsAux = jsonArray.getJSONObject(0);
				comic.setIdComic(Integer.parseInt(jsAux.getString("id")));

				//Obtain creators by comic
				JSONObject jsonObjectCreators = jsAux.getJSONObject("creators");
				JSONArray jsonArrayCreators = jsonObjectCreators.getJSONArray("items");
				creators = new ArrayList();
				for (int j = 0; j < jsonArrayCreators.length(); j++){
					creator = new Creator();
					JSONObject jsAuxCreator = jsonArrayCreators.getJSONObject(j);
					creator.setIdCreator(0);
					creator.setName(jsAuxCreator.getString("name"));
					creator.setRol(jsAuxCreator.getString("role"));
					creator.setComic(comic);
					creators.add(creator);
				}

				//Obtain coworkers by comic
				JSONObject jsonObjectCoWorkers = jsAux.getJSONObject("characters");
				JSONArray jsonArrayCoWorkers = jsonObjectCoWorkers.getJSONArray("items");
				coWorkers = new ArrayList();
				for (int j = 0; j < jsonArrayCoWorkers.length(); j++){
					coWorker = new CoWorker();
					JSONObject jsAuxCoWorker = jsonArrayCoWorkers.getJSONObject(j);
					coWorker.setIdCoWorker(0);
					coWorker.setName(jsAuxCoWorker.getString("name"));
					coWorker.setComic(comic);
					coWorkers.add(coWorker);
				}
				comic.setCreators(creators);
				comic.setCoWorkers(coWorkers);
			} catch (JSONException e) {
				log.error("ERROR {}", e);
			}
		}
		return comic;
	}
}
