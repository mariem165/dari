package Services;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Interfaces.ICommentRemote;
import tn.esprit.dari.entities.Comment;
@Stateful
@LocalBean
public class CommentService implements ICommentRemote {

		
		@PersistenceContext (unitName="dari-ejb")
		EntityManager em;
		

		@Override
		public void CreateComment(Comment M) {
			String S=M.getContent();
			String [] WordList= { "anal"," anus"," arse"," ass"," ballsack"," balls"," bastard"," bitch"," biatch"," bloody"," blowjob"," blow"," job"," bollock"," bollok"," boner"," boob"," bugger"," bum"," butt"," buttplug"," clitoris"," cock"," coon"," crap"," cunt"," damn"," dick"," dildo"," dyke"," fag"," feck"," fellate"," fellatio"," felching"," fuck"," f u c k"," fudgepacker"," fudge"," packer"," flange","goddamn","god damn"," hell"," homo"," jerk"," jizz"," knobend"," knob"," end"," labia"," lmao"," lmfao"," muff"," nigger"," nigga"," omg"," penis"," piss"," poop"," prick"," pube"," pussy"," queer"," scrotum"," sex"," shit"," slut"," smegma"," spunk"," tit"," tosser"," turd"," twat"," vagina"," wank"," whore"," wtf"};	
			boolean found = false;
		    for (String word : WordList) {
		        if (S.indexOf(word)!=-1) {
		            found = true;
		            break;
		        }
		    }
		    if (found==false) em.persist(M);
		}

		@Override
		public void DeleteComment(Comment M) {
			
			em.remove(M);
			
		}

		@Override
		public List<Comment> ShowComment() {
			List <Comment> MM= em.createQuery("Select a from Comment a",Comment.class).getResultList();
			return MM;
		}

		@Override
		public void UpdateComment( int id,Comment m2) {
			Comment m = em.find(Comment.class, id);
			 if (m2.getContent()!=null)m.setContent(m2.getContent());
			
		}

	}


