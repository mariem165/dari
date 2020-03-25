package Interfaces;

import java.util.List;

import tn.esprit.dari.entities.Comment;;

public interface ICommentRemote {
	public void CreateComment (Comment C);
	public void DeleteComment (Comment C);
	public List<Comment> ShowComment ();
	public void UpdateComment (int id,Comment C);

}
