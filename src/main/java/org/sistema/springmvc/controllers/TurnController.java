package org.sistema.springmvc.controllers;

import java.util.List;
import java.util.Map;


import org.sistema.springmvc.dao.TurnDAO;
import org.sistema.springmvc.dao.UserDAO;
import org.sistema.springmvc.models.Turn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for turns.
 * 
 * @author Eugenia Pérez Martínezo
 *
 */
@Controller
public class TurnController {

	private static final Logger logger = LoggerFactory
			.getLogger(TurnController.class);

	@Autowired
	private TurnDAO turnDAO;
	@Autowired
	private UserDAO userDAO;

	/**
	 * handles default /turns
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/turns"})
	 */

	@RequestMapping(method = RequestMethod.GET, value = {"/turns" })
	public String showTurns(Map<String, Object> model) {
		logger.info("Product showTurns. ");

		List<Turn> turns = turnDAO.selectAll();
		model.put("turns", turns);
		// For the form
		model.put("turn", new Turn());
		model.put("users", userDAO.selectAll());

		return "turn/turns";
	}

	/**
	 * handles default /turns/id
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/turns/{id}"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/turns/{id}" })
	public String turnDetail(@PathVariable(value = "id") Long id,
			Map<String, Object> model) {
		logger.info("Turn detail");

		Turn turn = turnDAO.selectById(id);
		model.put("turn", turn);

		return "turn/turnDetail";
	}

	/**
	 * handles /turns/new
	 * 
	 * @return the name of the view to show RequestMapping({"/turns/new"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/turns/new" })
	public String newTurn(Map<String, Object> model) {
		logger.info("Showing custom view GET ");

		model.put("turn", new Turn());

		return "turn/newTurn";
	}

	/**
	 * handles /turns/new by POST
	 * 
	 * @return the name of the view to show RequestMapping({"/turns/new"})
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/turns/new" })
	public ModelAndView createTurn(Turn turn) {

		ModelAndView modelAndView = new ModelAndView();

		/*if (bindingResult.hasErrors()) {
			modelAndView.setViewName("turn/newTurn");
			modelAndView.addObject("turn", turn);
			return modelAndView;
		}*/

		if (turnDAO.insert(turn) > 0) {
			logger.info("Saveview POST " + turn.getId());
			// We return view name
			modelAndView.setViewName("turn/created");
			modelAndView.addObject("turn", turn);
		} else {
			modelAndView.setViewName("error");
			modelAndView
					.addObject("error",
							"An error ocurred while trying to create a new turn. Please, try again");
		}
		return modelAndView;
	}

	/**
	 * Simply selects the update view
	 */
	@RequestMapping(value = "/turns/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable(value = "id") Long turnId, Model model) {
		logger.info("Showing update view GET ");

		// We find the product through DAO and load into model
		model.addAttribute("turn", turnDAO.selectById(turnId));

		return "turn/update";
	}

	/**
	 * Handles the POST from the Custom.jsp page to update the Turn.
	 */
	@RequestMapping(value = "/turns/saveupdate", method = RequestMethod.POST)
	public ModelAndView saveUpdate(Turn turn) {
		logger.info("Save update " + turn.getId());

		turnDAO.update(turn);

		ModelAndView modelAndView = new ModelAndView();

		// We pass the turn received through this object
		modelAndView.addObject("turn", turn);

		// The same as return "turn/saveUpdate"
		modelAndView.setViewName("turn/saveUpdated");
		return modelAndView;
	}

	/**
	 * Delete the specific turn
	 */
	@RequestMapping(value = "/turns/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") Long turnId, Model model) {
		logger.info("Product detail /delete");

		turnDAO.delete(turnId);
		model.addAttribute("turnId", turnId);

		return "turn/deleted";
	}

}
