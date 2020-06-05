package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Comment;;
@Remote
@LocalBean
public interface ICommentRemote {
	public void CreateComment (Comment C);
	public void DeleteComment (Comment C);
	public List<Comment> ShowComment ();
	public void UpdateComment (int id,Comment C);

}
