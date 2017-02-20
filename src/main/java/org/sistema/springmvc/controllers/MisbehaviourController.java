package org.sistema.springmvc.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.sistema.springmvc.dao.MisbehaviorDAO;
import org.sistema.springmvc.dao.UserDAO;
import org.sistema.springmvc.models.Misbehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for misbehaviors.
 * 
 * @author David Larrea
 *
 */
@Controller
public class MisbehaviourController {

	private static final Logger logger = LoggerFactory
			.getLogger(MisbehaviourController.class);

	@Autowired
	private MisbehaviorDAO misbehaviorDAO;
	@Autowired
	private UserDAO userDAO;

	/**
	 * handles default /misbehaviors
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/misbehaviors"})
	 */

	@RequestMapping(method = RequestMethod.GET, value = {"/misbehaviors" })
	public String showMisbehaviors(Map<String, Object> model) {
		logger.info("Product showMisbehaviors. ");

		List<Misbehavior> misbehaviors = misbehaviorDAO.selectAll();
		model.put("misbehaviors", misbehaviors);
		model.put("misbehaviorS", new Misbehavior());

		return "misbehavior/misbehaviors";
	}
	
	/**
	 * handles default /misbehaviors/search
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/misbehaviors"})
	 */

	@RequestMapping(method = RequestMethod.POST, value = {"/misbehaviors/search" })
	public String searchMisbehaviors(Misbehavior misbehavior, Map<String, Object> model) {
		logger.info("Product showMisbehaviors. ");

		List<Misbehavior> misbehaviors = misbehaviorDAO.selectByName(misbehavior.getTitle());
		model.put("misbehaviors", misbehaviors);
		model.put("misbehaviorS", new Misbehavior());

		return "misbehavior/misbehaviors";
	}

	/**
	 * handles default /misbehaviors/id
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/misbehaviors/{id}"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/misbehaviors/{id}" })
	public String misbehaviorDetail(@PathVariable(value = "id") Long id,
			Map<String, Object> model) {
		logger.info("Turn detail");

		model.put("misbehavior", misbehaviorDAO.selectById(id));
		model.put("misbehaviorS", new Misbehavior());

		return "misbehavior/misbehaviorDetail";
	}

	/**
	 * handles /misbehaviors/new
	 * 
	 * @return the name of the view to show RequestMapping({"/misbehaviors/new"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/misbehaviors/new" })
	public String newMisbehavior(Map<String, Object> model) {
		logger.info("Showing custom view GET ");

		model.put("misbehavior", new Misbehavior());
		model.put("misbehaviorS", new Misbehavior());
		model.put("users", userDAO.selectAll());

		return "misbehavior/newMisbehavior";
	}

	/**
	 * handles /misbehaviors/new by POST
	 * 
	 * @return the name of the view to show RequestMapping({"/misbehaviors/new"})
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/misbehaviors/new" })
	public ModelAndView createMisbehavior(@ModelAttribute("misbehavior") @Valid Misbehavior misbehavior, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("misbehavior/newMisbehavior");
			modelAndView.addObject("misbehavior", misbehavior);
			modelAndView.addObject("misbehaviorS", new Misbehavior());
			modelAndView.addObject("users", userDAO.selectAll());
			return modelAndView;
		}

		if (misbehaviorDAO.insert(misbehavior) > 0) {
			logger.info("Saveview POST " + misbehavior.getId());
			// We return view name
			modelAndView.setViewName("misbehavior/created");
			modelAndView.addObject("misbehaviorS", new Misbehavior());
			modelAndView.addObject("misbehavior", misbehavior);
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
	@RequestMapping(value = "/misbehaviors/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable(value = "id") Long misbehaviorId, Model model) {
		logger.info("Showing update view GET ");

		// We find the product through DAO and load into model
		model.addAttribute("misbehavior", misbehaviorDAO.selectById(misbehaviorId));
		model.addAttribute("users", userDAO.selectAll());
		model.addAttribute("misbehaviorS", new Misbehavior());

		return "misbehavior/update";
	}

	/**
	 * Handles the POST from the Custom.jsp page to update the misbehavior.
	 */
	@RequestMapping(value = "/misbehaviors/saveupdate", method = RequestMethod.POST)
	public ModelAndView saveUpdate(Misbehavior misbehavior) {
		logger.info("Save update " + misbehavior.getId());
		ModelAndView modelAndView = new ModelAndView();

		misbehaviorDAO.update(misbehavior);

		// We pass the turn received through this object
		modelAndView.addObject("misbehavior", misbehavior);
		modelAndView.addObject("misbehaviorS", new Misbehavior());

		// The same as return "turn/saveUpdate"
		modelAndView.setViewName("misbehavior/saveUpdated");
		return modelAndView;
	}

	/**
	 * Delete the specific misbehavior
	 */
	@RequestMapping(value = "/misbehaviors/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") Long misbehaviorId, Model model) {
		logger.info("Product detail /delete");

		misbehaviorDAO.delete(misbehaviorId);
		model.addAttribute("misbehaviorId", misbehaviorId);
		model.addAttribute("misbehaviorS", new Misbehavior());

		return "misbehavior/deleted";
	}
	
	/**
	 * Delete the specific employee
	 */
	@RequestMapping(value = "/misbehaviors/deleteall", method = RequestMethod.GET)
	public String deleteAll(Map<String, Object> model) {
		logger.info("Misbehavior deleteAll");
		misbehaviorDAO.deleteAll();

		model.put("misbehaviorS", new Misbehavior());

		return "misbehavior/misbehaviors";
	}

}
